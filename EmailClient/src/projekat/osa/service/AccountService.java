package projekat.osa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.osa.entity.Account;
import projekat.osa.repository.AccountRepository;

@Service
public class AccountService implements AccountServiceInterface {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
		
	}

	@Override
	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public Account findOne(Integer accountId) {
		
		return accountRepository.findOne(accountId);
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account findByUsernameAndPassword(String username, String password) {
		return accountRepository.findByUsernameAndPassword(username, password);
	}
	
	
	@Override
	public void remove(Integer id) {
		accountRepository.delete(id);
	}

}
