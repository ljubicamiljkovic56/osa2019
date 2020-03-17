package projekat.osa.service;

import java.util.List;


import projekat.osa.entity.Message;


public interface MessageServiceInterface {

	Message findOne(Integer messageId);
	
	List<Message> findAll();
	
	Message save(Message message);
	
	void remove(Integer id);
	
	List<Message> findBySubject(String subject);

}
