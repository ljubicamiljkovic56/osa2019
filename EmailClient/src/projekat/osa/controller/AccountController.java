package projekat.osa.controller;

import java.util.ArrayList;
import java.util.List;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import projekat.osa.service.AccountServiceInterface;
import projekat.osa.service.UserServiceInterface;
import projekat.osa.entity.User;
import projekat.osa.dto.AccountDTO;

import projekat.osa.entity.Account;



@RestController
@RequestMapping(value="api/accounts")
public class AccountController {

	
	@Autowired
    private AccountServiceInterface accountService;
	
	
	@Autowired
    private UserServiceInterface userService;
	
	
	@GetMapping(value = "/getallaccount/{username}")
	public ResponseEntity<List<AccountDTO>> getAllAccount(@PathVariable("username") String username){
		List<Account> accounts = accountService.findAll();
		
		if (accounts == null) {
			return new ResponseEntity<List<AccountDTO>>(HttpStatus.NOT_FOUND);
		}
		User user = userService.findByUsername(username);
		List<AccountDTO> AccountDTO = new ArrayList<AccountDTO>();
		for (Account account : accounts) {
			
			if(account.getUser().getId() == user.getId()) {
			
			AccountDTO.add(new AccountDTO(account));
			}
			
		}
		return new ResponseEntity<List<AccountDTO>>(AccountDTO, HttpStatus.OK);
	}
	
	

	  
	@GetMapping(value="/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") Integer id){
		Account account = accountService.findOne(id);
		if(account == null){
			return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);
	}
	
	
	@PutMapping(value="/update", consumes="application/json")
	public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO){
		Account account = accountService.findOne(accountDTO.getId());
		if (account == null) {
			return new ResponseEntity<AccountDTO>(HttpStatus.BAD_REQUEST);
		}
		
		account.setDisplayName(accountDTO.getDisplayName());
		account.setPassword(accountDTO.getPassword());
		account.setUsername(accountDTO.getUsername());
		
		account = accountService.save(account);
		System.out.println("ACCOUNT CHANGED.....");
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable("id") Integer id){
		Account account = accountService.findOne(id);
		if (account != null){
			accountService.remove(id);
			System.out.println("ACCOUNT DELETED.....");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
