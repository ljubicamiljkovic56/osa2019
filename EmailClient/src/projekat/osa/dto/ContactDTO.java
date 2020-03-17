package projekat.osa.dto;



import java.io.Serializable;

import projekat.osa.entity.Contact;



public class ContactDTO implements Serializable {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String note;
    private PhotoDTO photo;
    private String displayName;
    

    public ContactDTO(){
        super();
    }
    
	public ContactDTO(Integer id, String firstname, String lastname, String email, String note, PhotoDTO photo,
			String displayName, UserDTO user) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.note = note;
		this.photo = photo;
		this.displayName = displayName;
	}

	public ContactDTO(Contact c) {
    	this.id = c.getId();
    	this.firstname = c.getFirstName();
    	this.lastname = c.getLastName();
    	this.email = c.getEmail();
    	this.note = c.getNote();
    	this.displayName = c.getDisplayName();
    	if(c.getPhoto() != null)
    		this.photo = new PhotoDTO(c.getPhoto());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst() {
        return firstname;
    }

    public void setFirst(String first) {
        this.firstname = first;
    }

    public String getLast() {
        return lastname;
    }

    public void setLast(String last) {
        this.lastname = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDisplayName() {
		return displayName;
	}

	public PhotoDTO getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoDTO photo) {
		this.photo = photo;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
    
}
