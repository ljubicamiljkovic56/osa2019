package projekat.osa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import projekat.osa.dto.ContactDTO;
import projekat.osa.entity.Contact;
import projekat.osa.entity.Photo;
import projekat.osa.entity.User;
import projekat.osa.service.ContactServiceInterface;


import projekat.osa.service.UserServiceInterface;


@RestController
@RequestMapping(value="api/contacts")
public class ContactController {
	
	@Autowired
	private ContactServiceInterface contactService;
	
	@Autowired
    private UserServiceInterface userService;
	
	@GetMapping
	public ResponseEntity<List<ContactDTO>> getContacts() {
		List<Contact> contacts = contactService.findAll();
		List<ContactDTO> contactsDTO = new ArrayList<ContactDTO>();
		for (Contact c : contacts) {
			contactsDTO.add(new ContactDTO(c));
		}
		return new ResponseEntity<List<ContactDTO>>(contactsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ContactDTO> getContact(@PathVariable("id") Integer id){
		Contact contact = contactService.findOne(id);
		if(contact == null){
			return new ResponseEntity<ContactDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);
	}
	
	@GetMapping(value="/byUser/{username}")
	public ResponseEntity<List<ContactDTO>> getContactsByUsername(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		if (user == null) {
			return new ResponseEntity<List<ContactDTO>>(HttpStatus.NOT_FOUND);
		}
		List<Contact> contacts = contactService.findAll();
		List<ContactDTO> contactsDTO = new ArrayList<ContactDTO>();
		for (Contact c : contacts) {
			if(c.getUser().getId() == user.getId()) {
				contactsDTO.add(new ContactDTO(c));
			}
		}
		return new ResponseEntity<List<ContactDTO>>(contactsDTO, HttpStatus.OK);
	}
	
	@PostMapping(value="/add/{username}", consumes="application/json")
	public ResponseEntity<ContactDTO> saveContact(@RequestBody ContactDTO contactDTO, @PathVariable("username") String username){
		User user = userService.findByUsername(username);
		if (user == null) {
			return new ResponseEntity<ContactDTO>(HttpStatus.BAD_REQUEST);
		}
		Contact contact = new Contact();
		contact.setEmail(contactDTO.getEmail());
		contact.setFirstName(contactDTO.getFirst());
		contact.setLastName(contactDTO.getLast());
		contact.setDisplayName(contactDTO.getDisplayName());
		if(contactDTO.getNote() != null)
			contact.setNote(contactDTO.getNote());
		contact.setUser(user);
		
		if(contactDTO.getPhoto() != null)
			contact.setPhoto(new Photo(contactDTO.getPhoto().getId(), contactDTO.getPhoto().getPath(), contact));
	
		contact = contactService.save(contact);
		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/update/{id}", consumes="application/json")
	public ResponseEntity<ContactDTO> updateProduct(@RequestBody ContactDTO contactDTO, @PathVariable("id") Integer id){
		Contact contact = contactService.findOne(id); 
		
		if (contact == null) {
			return new ResponseEntity<ContactDTO>(HttpStatus.BAD_REQUEST);
		}
		
		contact.setEmail(contactDTO.getEmail());
		contact.setFirstName(contactDTO.getFirst());
		contact.setLastName(contactDTO.getLast());
		contact.setDisplayName(contactDTO.getDisplayName());
		if(contactDTO.getNote() != null)
			contact.setNote(contactDTO.getNote());
		
		if(contactDTO.getPhoto() != null)
			contact.setPhoto(new Photo(contactDTO.getPhoto().getId(), contactDTO.getPhoto().getPath(), contact));
	
		contact = contactService.save(contact);
		
		return new ResponseEntity<ContactDTO>(new ContactDTO(contact), HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable("id") Integer id){
		Contact contact = contactService.findOne(id);
		if (contact != null){
			contactService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
