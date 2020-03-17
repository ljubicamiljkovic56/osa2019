package projekat.osa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.osa.entity.Contact;
import projekat.osa.repository.ContactRepository;

@Service
public class ContactService implements ContactServiceInterface {
	
	@Autowired
	ContactRepository contactRepository;
	
	@Override
	public Contact findOne(Integer productId){
		return contactRepository.findOne(productId);
	}
	
	@Override
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public void remove(Integer id) {
		contactRepository.delete(id);
	}

}
