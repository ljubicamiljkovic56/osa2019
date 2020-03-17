package projekat.osa.controller;

import java.util.HashSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projekat.osa.dto.AccountDTO;
import projekat.osa.dto.UserDTO;
import projekat.osa.entity.Account;
import projekat.osa.entity.Contact;
import projekat.osa.entity.Folder;
import projekat.osa.entity.Message;
import projekat.osa.entity.Tag;
import projekat.osa.entity.User;
import projekat.osa.service.AccountServiceInterface;
import projekat.osa.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/users")
public class UserController {
	
	@Autowired
    private AccountServiceInterface accountService;
	
	
	@Autowired
    private UserServiceInterface userService;
	
	@PostMapping(value = "/loginUser")
	public ResponseEntity<Void> loginUser(@RequestParam("username") String username, @RequestParam("password") String password){
		System.out.println("LOGIN..........");
		User user = userService.findByUsernameAndPassword(username, password);
		if (user == null) {
			System.out.println("Neuspesna prijava");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		System.out.println(user.getUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/getUser")
	public ResponseEntity<UserDTO> getUser(@RequestParam("username") String username, @RequestParam("password") String password){
		User user = userService.findByUsernameAndPassword(username, password);
		
		if (user == null) {
			System.out.println("Neuspesan get");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user) ,HttpStatus.OK);
	}
	
	@PostMapping(value = "/registrationUser")
	public ResponseEntity<Void> registrationUser(@RequestBody UserDTO userDTO){
		User user = new User();
		user.setFirstName(userDTO.getFirstname());
	
		user.setLastName(userDTO.getLastname());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		user.setUserTags(new HashSet<Tag>());
		user.setUserAccounts(new HashSet<Account>());
		user.setUserContacts(new HashSet<Contact>());
		
		System.out.println("REGISTRATION.....");
		userService.save(user);
		
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@PutMapping(value = "/addAccaunt/{username}")
	public ResponseEntity<Void> addAccaunt(@RequestBody AccountDTO accountDTO,@PathVariable("username") String username){
		
		User user = userService.findByUsername(username);
		
		Account account=new Account();
		account.setAccountFolders(new HashSet<Folder>());
		account.setDisplayName(accountDTO.getDisplayName());
		
		account.setInServerAddress("");
		account.setInServerPort(2230);
		account.setInServerType(123);
		account.setAccountMessages(new HashSet<Message>());
		account.setPassword(accountDTO.getPassword());
		account.setSmtpAddress("smtp.gmail.com\"");
		account.setSmtpPort(2233);
		account.setUser(user);
		account.setUsername(accountDTO.getUsername());
		
		accountService.save(account);
		
		
		
		System.out.println("ACCOUNT ADDED.....");
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@PutMapping(value = "/updateUser")
	public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO) {
		User user = userService.findOne(userDTO.getId());
		
		user.setFirstName(userDTO.getFirstname());
		user.setLastName(userDTO.getLastname());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		
		userService.save(user);
		System.out.println("PROFILE INFO CHANGED.....");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
}
