package projekat.osa.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projekat.osa.entity.Account;
import projekat.osa.entity.Contact;

import projekat.osa.entity.Tag;
import projekat.osa.entity.User;

public class UserDTO implements Serializable {
	
	private Integer id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private List<AccountDTO> userAccounts;
	private List<ContactDTO> userContacts;
	private List<TagDTO> userTags;
	
	public UserDTO(Integer id, String username, String password, String firstname, String lastname, List<AccountDTO> userAccounts, List<ContactDTO> userContacts, List<TagDTO> userTags) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.userAccounts = userAccounts;
		this.userContacts = userContacts;
		this.userTags = userTags;
	}

	public UserDTO() {
		super();
	}
	
	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstname = user.getFirstName();
		this.lastname = user.getLastName();
		
    	List<AccountDTO> accountDTOs = new ArrayList<>(); 
    	if (user.getUserAccounts() != null) {
	    	for (Account itAccount : user.getUserAccounts()) {
	    		accountDTOs.add(new AccountDTO(itAccount));
			}	
		}
    	this.userAccounts = accountDTOs;
    	
    	List<ContactDTO> contactDTOs = new ArrayList<>(); 
    	if (user.getUserContacts() != null) {
	    	for (Contact itContact : user.getUserContacts()) {
	    		contactDTOs.add(new ContactDTO(itContact));
			}	
		}
    	this.userContacts = contactDTOs;
    	
    	List<TagDTO> tagDTOs = new ArrayList<>(); 
    	if (user.getUserTags() != null) {
	    	for (Tag itTag : user.getUserTags()) {
	    		tagDTOs.add(new TagDTO(itTag));
			}	
		}
    	this.userTags = tagDTOs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<AccountDTO> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(List<AccountDTO> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public List<ContactDTO> getUserContacts() {
		return userContacts;
	}

	public void setUserContacts(List<ContactDTO> userContacts) {
		this.userContacts = userContacts;
	}

	public List<TagDTO> getUserTags() {
		return userTags;
	}

	public void setUserTags(List<TagDTO> userTags) {
		this.userTags = userTags;
	}
	
	

}
