package projekat.osa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.osa.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
