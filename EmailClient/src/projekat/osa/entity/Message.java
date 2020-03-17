package projekat.osa.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;

@Entity
@Table(name="messages")
public class Message implements Serializable {

	@Id
	  @GeneratedValue(strategy=IDENTITY)
	  @Column(name="message_id", unique=true, nullable=false)
	  private Integer id;
	  
	  @Column(name="message_from", unique=false, nullable=false)
	  private String from;
	  
	  @Column(name="message_to", unique=false, nullable=false)
	  private String to;
	  
	  @Column(name="cc", unique=false, nullable=true)
	  private String cc;
	  
	  @Column(name="bcc", unique=false, nullable=true)
	  private String bcc;
	  
	  @Temporal(TIMESTAMP)
	  @Column(name="datem", unique=false, nullable=false)
	  private Date date;
	  
	  @Column(name="subject", unique=false, nullable=false)
	  private String subject;
	  
	  @Column(name="content", unique=false, nullable=true)
	  private String content;
	  
	  @Column(name="unread", unique=false, nullable=false)
	  private boolean unread;
	  
	  @ManyToOne
	  @JoinColumn(name="folder_id", referencedColumnName="folder_id", nullable=false)
	  private Folder folder;
	  
	  @ManyToOne
	  @JoinColumn(name="account_id", referencedColumnName="account_id", nullable=false)
	  private Account account;
	  
	  @OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="message")
	  private Set<Attachment> messageAttachment = new HashSet<Attachment>();
	  
	  @OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="messageTag")
	  private Set<Tag> messageTags = new HashSet<Tag>();

	  
	public Message() {
		super();
	}
	
	public void addAttachment(Attachment attachment) {
		if (attachment.getMessage() != null) {
			attachment.getMessage().getMessageAttachment().remove(attachment);
		}
		attachment.setMessage(this);
		getMessageAttachment().add(attachment);
	}
	
	public void removeAttachment(Attachment attachment) {
		attachment.setMessage(null);
		getMessageAttachment().remove(attachment);
	}
	  
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date dateM) {
		this.date = dateM;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean getUnread() {
		return unread;
	}
	public void setUnread(boolean unread) {
		this.unread = unread;
	}
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}


	public Set<Attachment> getMessageAttachment() {
		return messageAttachment;
	}


	public void setMessageAttachment(Set<Attachment> messageAttachment) {
		this.messageAttachment = messageAttachment;
	}


	public Set<Tag> getMessageTags() {
		return messageTags;
	}


	public void setMessageTags(Set<Tag> messageTags) {
		this.messageTags = messageTags;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", from=" + from + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", date=" + date
				+ ", subject=" + subject + ", content=" + content + ", unread=" + unread + ", folder=" + folder
				+ ", account=" + account + ", messageAttachment=" + messageAttachment + ", messageTags=" + messageTags
				+ "]";
	}
	
	
	
}
