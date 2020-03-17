package projekat.osa.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.osa.entity.Message;
import projekat.osa.repository.MessageRepository;


@Service
public class MessageService implements MessageServiceInterface {
	
	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public Message findOne(Integer messageId){
		return messageRepository.findOne(messageId);
	}
	
	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

	@Override
	public Message save(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public void remove(Integer id) {
		messageRepository.delete(id);
	}
	
	@Override
	public List<Message> findBySubject(String subject) {
		return messageRepository.findBySubject(subject);
	}

}
