package projekat.osa.service;

import java.util.List;

import projekat.osa.entity.Account;

public interface AccountServiceInterface {
	
	Account save(Account account);
    
	Account findByUsername(String username);

	Account findOne(Integer accountId);
	
	List<Account> findAll();
	
	Account findByUsernameAndPassword(String username, String password);

	void remove(Integer id);

}
