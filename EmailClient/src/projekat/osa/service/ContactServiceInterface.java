package projekat.osa.service;

import java.util.List;

import projekat.osa.entity.Contact;


public interface ContactServiceInterface {

	Contact findOne(Integer contactId);
	
	List<Contact> findAll();
	
	Contact save(Contact contact);
	
	void remove(Integer id);

}
