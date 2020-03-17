package projekat.osa.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity                 
@Table(name="photos")
public class Photo implements Serializable{

	@Id                                 
	  @GeneratedValue(strategy=IDENTITY)
	  @Column(name="photo_id", unique=true, nullable=false) 
	  private Integer id;
	  	
	  @Column(name="path", unique=false, nullable=false)
	  private String path;
	  
	  @ManyToOne
	  @JoinColumn(name="contact_id", referencedColumnName="contact_id", nullable=false)
	  private Contact contact;

	  
	public Photo() {
		super();
	}
	
	public Photo(Integer id, String path, Contact contact) {
		super();
		this.id = id;
		this.path = path;
		this.contact = contact;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", path=" + path + "]";
	}		  
	  
}
