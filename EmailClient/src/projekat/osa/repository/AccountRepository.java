package projekat.osa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.osa.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	Account findByUsername(String username);
	
	Account findByUsernameAndPassword(String username, String password);

}
