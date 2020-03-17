package projekat.osa.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity                 
@Table(name="folders")
public class Folder implements Serializable{

	  @Id                                 
	  @GeneratedValue(strategy=IDENTITY)
	  @Column(name="folder_id", unique=true, nullable=false) 
	  private Integer id;
	  	
	  @Column(name="name", unique=false, nullable=false)
	  private String name;
	  
	  @ManyToOne
	  @JoinColumn(name="parent_folder_id", referencedColumnName="folder_id", nullable=true)
	  private Folder parent;
	  
	  @ManyToOne
	  @JoinColumn(name="account_id", referencedColumnName="account_id", nullable=false)
	  private Account account;

	  @OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="folder")
	  private Set<Message> messages = new HashSet<Message>();
	  
	  @OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="parent")
	  private Set<Folder> subfolders = new HashSet<Folder>();
	  
	  @OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="destination")
	  private Set<Rule> rules = new HashSet<Rule>();
	  
	public Folder() {
		super();
		
	}
	
	public Folder(String name, Account account, Set<Rule> rules, Set<Message> messages, Folder parent,
			Set<Folder> subfolders) {
		super();
		this.name = name;
		this.account = account;
		this.rules = rules;
		this.messages = messages;
		this.parent = parent;
		this.subfolders = subfolders;
	}
	
	public void addRule(Rule rule) {
		if (rule.getDestination() != null) {
			rule.getDestination().getRules().remove(rule);
		}
		rule.setDestination(this);
		getRules().add(rule);
	}
	
	public void addMessage(Message message) {
		if (message.getFolder() != null) {
			message.getFolder().getMessages().remove(message);
		}
		message.setFolder(this);
		getMessages().add(message);
	}
	
	public void addFolder(Folder folder) {
		if (folder.getParentFolder() != null) {
			folder.getParentFolder().getSubfolders().remove(folder);
		}
		folder.setParentFolder(this);
		getSubfolders().add(folder);
	}
	
	public void removeRule(Rule rule) {
		rule.setDestination(null);
		getRules().remove(rule);
	}
	
	public void removeMessage(Message message) {
		message.setFolder(null);
		getMessages().remove(message);
	}
	
	public void removeFolder(Folder folder) {
		folder.setParentFolder(null);
		getSubfolders().remove(folder);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Folder getParentFolder() {
		return parent;
	}

	public void setParentFolder(Folder parentFolder) {
		this.parent = parentFolder;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<Folder> getSubfolders() {
		return subfolders;
	}

	public void setSubfolders(Set<Folder> subfolders) {
		this.subfolders = subfolders;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}

	@Override
	public String toString() {
		return "Folder [id=" + id + ", name=" + name + ", folder=" + parent + "]";
	}
	  
	  
	  
	  
}
