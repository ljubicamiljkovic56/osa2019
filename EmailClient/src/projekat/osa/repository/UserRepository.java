package projekat.osa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.osa.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
}
