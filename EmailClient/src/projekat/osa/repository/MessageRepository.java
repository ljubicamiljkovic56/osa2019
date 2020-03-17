package projekat.osa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.osa.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	List<Message> findBySubject(String subject);
}
