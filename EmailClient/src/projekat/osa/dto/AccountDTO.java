package projekat.osa.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projekat.osa.entity.Account;
import projekat.osa.entity.Folder;
import projekat.osa.entity.Message;

public class AccountDTO implements Serializable {

    private Integer id;
    private String smtpAdress;
    private int smtpPort;
    private int inServerType;
    private String inServerAdress;
    private int inServerPort;
    private String username;
    private String password;
    private String displayName;
    private List<MessageDTO> messages;
    private List<FolderDTO> folders;
    private String token;


    public AccountDTO() {
        super();
    }


	public AccountDTO(Integer id, String smtpAdress, int smtpPort, int inServerType, String inServerAdress,
			int inServerPort, String username, String password, String displayName,List<MessageDTO> messages, List<FolderDTO> folders, String token) {
		super();
		this.id = id;
		this.smtpAdress = smtpAdress;
		this.smtpPort = smtpPort;
		this.inServerType = inServerType;
		this.inServerAdress = inServerAdress;
		this.inServerPort = inServerPort;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.messages = messages;
		this.folders = folders;
		this.token = token;
	}
	
    public AccountDTO(Account a) {
		this.id = a.getId();
		this.smtpAdress = a.getSmtpAddress();
		this.smtpPort = a.getSmtpPort();
		this.inServerType = a.getInServerType();
		this.inServerAdress = a.getInServerAddress();
		this.inServerPort = a.getInServerPort();
		this.username = a.getUsername();
		this.password = a.getPassword();
		this.displayName = a.getDisplayName();
    	List<MessageDTO> messageDTOs = new ArrayList<>(); 
    	if (a.getAccountMessages() != null) {
	    	for (Message itMessage : a.getAccountMessages()) {
				messageDTOs.add(new MessageDTO(itMessage));
			}	
		}
    	this.messages = messageDTOs;
    	List<FolderDTO> folderDTOs = new ArrayList<>(); 
    	if (a.getAccountFolders() != null) {
	    	for (Folder itFolder : a.getAccountFolders()) {
	    		folderDTOs.add(new FolderDTO(itFolder));
			}	
		}
    	this.folders = folderDTOs;
		this.token = a.getToken();
    }


	public int getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getSmtpAdress() {
		return smtpAdress;
	}


	public void setSmtpAdress(String smtpAdress) {
		this.smtpAdress = smtpAdress;
	}


	public int getSmtpPort() {
		return smtpPort;
	}


	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}


	public int getInServerType() {
		return inServerType;
	}


	public void setInServerType(int inServerType) {
		this.inServerType = inServerType;
	}


	public String getInServerAdress() {
		return inServerAdress;
	}


	public void setInServerAdress(String inServerAdress) {
		this.inServerAdress = inServerAdress;
	}


	public int getInServerPort() {
		return inServerPort;
	}


	public void setInServerPort(int inServerPort) {
		this.inServerPort = inServerPort;
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


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public List<MessageDTO> getMessages() {
		return messages;
	}


	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}


	public List<FolderDTO> getFolders() {
		return folders;
	}


	public void setFolders(List<FolderDTO> folders) {
		this.folders = folders;
	}
	
	

    
}