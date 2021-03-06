package projekat.osa.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.osa.dto.AttachmentDTO;
import projekat.osa.dto.ConditionDTO;
import projekat.osa.dto.MessageDTO;
import projekat.osa.dto.OperationDTO;
import projekat.osa.dto.TagDTO;
import projekat.osa.entity.Account;
import projekat.osa.entity.Attachment;
import projekat.osa.entity.Folder;
import projekat.osa.entity.Message;
import projekat.osa.entity.Rule;
import projekat.osa.entity.Tag;
import projekat.osa.entity.User;
import projekat.osa.mailAPI.EmailReceiver;
import projekat.osa.mailAPI.EmailSender;
import projekat.osa.service.AccountServiceInterface;

import projekat.osa.service.FolderServiceInterface;

import projekat.osa.service.MessageServiceInterface;
import projekat.osa.service.UserServiceInterface;


@RestController
@RequestMapping(value="api/messages")
public class MessageController {
	
	@Autowired
	private MessageServiceInterface messageService;
	
	@Autowired
	private AccountServiceInterface accountService;
	
	@Autowired
	private FolderServiceInterface folderService;
	
	@Autowired
    private UserServiceInterface userService;
	
	
	@GetMapping
	public ResponseEntity<List<MessageDTO>> getAllMessages() {
		List<Message> messages = messageService.findAll();
		List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
		for (Message m : messages) {
			messagesDTO.add(new MessageDTO(m));
		}
		return new ResponseEntity<List<MessageDTO>>(messagesDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<MessageDTO> getMessage(@PathVariable("id") int id){
		Message message = messageService.findOne(id);
		if(message == null){
			return new ResponseEntity<MessageDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.OK);
	}
	
	@GetMapping(value="/byUser/{username}")
	public ResponseEntity<List<MessageDTO>> getMessagesByUsername(@PathVariable("username") String username){
		List<MessageDTO> messages = new ArrayList<>();
		User user = userService.findByUsername(username);
		for(Account a : user.getUserAccounts()) {
			for(Message m : a.getAccountMessages()) {
				messages.add(new MessageDTO(m));
			}
		}
		if(messages.isEmpty()){
			return new ResponseEntity<List<MessageDTO>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<MessageDTO>>(messages, HttpStatus.OK);
	}
	
	@GetMapping(value = "/emails/{accountId}")
	public ResponseEntity<List<MessageDTO>> getAllMessagesFromUser(@PathVariable("account_id") int id){
		EmailReceiver emailReceiver = new EmailReceiver(messageService, folderService);
		Account account = accountService.findOne(id);
		if (account == null) {
			return new ResponseEntity<List<MessageDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		Date date = account.getAccountMessages().stream().map(Message::getDate).max(Date::compareTo).get();
		emailReceiver.receiveEmail(account, date);
		List<MessageDTO> messageDTOs = new ArrayList<>();
		for (Message itMessage : account.getAccountMessages()) {
			if (itMessage.getFolder().getName().equals("Inbox")) {
				messageDTOs.add(new MessageDTO(itMessage));
			}

		}
		System.out.println("id ulogovanog: "+ account.getId());
		System.out.println("username : "+ account.getUsername());
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}
	
	
	
	
	
	
	@PostMapping(value = "/send/{username}", consumes="application/json")
	public ResponseEntity<Void> send(@RequestBody MessageDTO messageDTO, @PathVariable("username") String accountUsername){
		
		Account account = accountService.findByUsername(accountUsername + ".com");
		
		if (account == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		
		User user = account.getUser();
		Folder folder = folderService.findByNameAndAccount("Sent", account);
		if (folder == null) {
			Rule rule = new Rule(ConditionDTO.SUBJECT, "Sent", OperationDTO.MOVE, null);
			Set<Rule> rules = new HashSet<Rule>();
			rules.add(rule);
			folder = new Folder("Sent", account, rules, new HashSet<Message>(), null, null);
			rule.setDestination(folder);
		}
		Message message = null;
		/*if (messageService.findOne(messageDTO.getId()) != null) {
			message = messageService.findOne(messageDTO.getId());
		}
		
		if (message != null && message.getFolder().getName().equals("Drafts")) {
			messageService.remove(message.getId());
		} else {
			message = new Message();
		}*/
		message = new Message();
		getToCCBccToString(messageDTO, message);
		message.setAccount(account);
		account.addMessage(message);
		
		
		if (messageDTO.getAttachments() != null) {			
			for (AttachmentDTO itAttachmentDTO : messageDTO.getAttachments()) {
				Attachment attachment = new Attachment();
				System.out.println(itAttachmentDTO.getName());
				attachment.setData(itAttachmentDTO.getData().toString());
				attachment.setMessage(message);
				attachment.setMimeType(itAttachmentDTO.getType());
				attachment.setName(itAttachmentDTO.getName());
				
				message.addAttachment(attachment);
			}
		}
		message.setDate(messageDTO.getDateTime());
		folder.addMessage(message);
		message.setFrom(messageDTO.getFrom());
		message.setSubject(messageDTO.getSubject());
		message.setContent(messageDTO.getContent());
		Tag tag = new Tag();
		if (messageDTO.getTags() != null) {
			for (TagDTO itTagDTO : messageDTO.getTags()) {
				tag.setId(itTagDTO.getId());
				tag.setName(itTagDTO.getName());
				tag.setUserTag(user);
				message.getMessageTags().add(tag);
			}
		}
		message.setUnread(messageDTO.isUnread());
		message.setSubject(messageDTO.getSubject());
		
		folder = folderService.save(folder);
		
		EmailSender mailSender = new EmailSender(messageService);
		mailSender.sendEmail(message);
		System.out.println(message.toString());
		
//		Message message = new Message(55, account.getUsername(), "", "", "", dateTime, subject, content, unread, account, folder, attachments, tags)
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteMessage(@PathVariable("id") Integer id){
		Message message = messageService.findOne(id);
		if (message != null){
			messageService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	public static void getToCCBccToString(MessageDTO messageDTO, Message message) {
		StringBuffer sb;
		
		if (messageDTO.getTo() != null) {
			sb = new StringBuffer();
			for (int i = 0; i < messageDTO.getTo().size(); i++) {
				if (i != 0) {
					sb.append(";");
				}
				sb.append(messageDTO.getTo().get(i));
				

			} 
			message.setTo(sb.toString());
		} else {
			message.setTo("DRAFT");
		}
		
		if (messageDTO.getCc() != null) {
			sb = new StringBuffer();
			for (int i = 0; i < messageDTO.getCc().size(); i++) {
				if (i != 0) {
					sb.append(";");
				}
				sb.append(messageDTO.getCc().get(i));
				

			} 
			message.setCc(sb.toString());
			if(message.getCc().equals(""))
				message.setCc(null);
		} else {
			message.setCc(null);
		}
		
		
		if (messageDTO.getBcc() != null) {
			sb = new StringBuffer();
			for (int i = 0; i < messageDTO.getBcc().size(); i++) {
				if (i != 0) {
					sb.append(";");
				}
				sb.append(messageDTO.getBcc().get(i));
				

			} 
			message.setBcc(sb.toString());
			if(message.getBcc().equals(""))
				message.setBcc(null);
		} else {
			message.setBcc(null);
		}
		
		
	}
}
