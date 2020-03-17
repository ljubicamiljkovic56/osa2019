package projekat.osa.entity;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="attachments")
public class Attachment implements Serializable{

	@Id
	  @GeneratedValue(strategy=IDENTITY)
	  @Column(name="tag_id", unique=true, nullable=false)
	  private Integer id;
	  
	  @Basic(fetch=LAZY)
	  @Column(name="data", unique=false, nullable=false)
	  private String data;
	
	  @Column(name="mime_type", unique=false, nullable=false)
	  private String mimeType;
	  
	  @Column(name="name", unique=false, nullable=false)
	  private String name;
	
	  @ManyToOne
	  @JoinColumn(name="message_id", referencedColumnName="message_id", nullable=false)
	  private Message message;

	  
	public Attachment() {
		super();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	  
}
