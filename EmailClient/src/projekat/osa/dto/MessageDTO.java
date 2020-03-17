package projekat.osa.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import projekat.osa.entity.Attachment;

import projekat.osa.entity.Message;
import projekat.osa.entity.Tag;

public class MessageDTO implements Serializable {

    private Integer id;
    private String from;
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private Date dateTime;
    private String subject;
    private String content;
    private boolean unread;
    private List<AttachmentDTO> attachments;
    private List<TagDTO> tags;

    public MessageDTO(){

    }

     
    public MessageDTO(Integer id, String from, List<String> to, List<String> cc,
    		List<String> bcc, Date dateTime, String subject, String content, boolean unread, List<AttachmentDTO> attachments, List<TagDTO> tags) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.dateTime = dateTime;
		this.subject = subject;
		this.content = content;
		this.unread = unread;
        this.attachments = attachments;
        this.tags = tags;
	}

	public MessageDTO(Message m) {
    	this.id = m.getId();
    	this.from = m.getFrom();
    	this.setTo(new ArrayList<>());
    	if (m.getTo() != null && !m.getTo().equals("")) {
			StringTokenizer token =  new StringTokenizer(m.getTo(), ";");
			while (token.hasMoreTokens()) {
				this.getTo().add(token.nextToken());
			}
		}
    	this.setCc(new ArrayList<>());
    	if (m.getCc() != null && !m.getCc().equals("")) {
			StringTokenizer token =  new StringTokenizer(m.getCc(), ";");
			while (token.hasMoreTokens()) {
				this.getCc().add(token.nextToken());
			}
		}
    	this.setBcc(new ArrayList<>());
    	if (m.getBcc() != null && !m.getBcc().equals("")) {
			StringTokenizer token =  new StringTokenizer(m.getBcc(), ";");
			while (token.hasMoreTokens()) {
				this.getBcc().add(token.nextToken());
			}
		}
        this.dateTime = m.getDate();
        this.subject = m.getSubject();
        this.content = m.getContent();
        
    	List<AttachmentDTO> attachmentDTOs = new ArrayList<>();
    	for (Attachment itAttachment : m.getMessageAttachment()) {
    		attachmentDTOs.add(new AttachmentDTO(itAttachment));
		}
    	this.attachments = attachmentDTOs;
    	List<TagDTO> tagDTOs = new ArrayList<>();
    	if (m.getMessageTags() != null) {
        	for (Tag itTag : m.getMessageTags()) {
    			tagDTOs.add(new TagDTO(itTag));
    		}
		}
    	this.tags = tagDTOs;

    }
    
    @Override
    public String toString() {
        return from + ": " + "Subject: " + subject + " " + "Content: " + content + " ";
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

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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

	public boolean isUnread() {
		return unread;
	}


	public void setUnread(boolean unread) {
		this.unread = unread;
	}


	public List<AttachmentDTO> getAttachments() {
		return attachments;
	}


	public void setAttachments(List<AttachmentDTO> attachments) {
		this.attachments = attachments;
	}


	public List<TagDTO> getTags() {
		return tags;
	}


	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}
	
	
    
    
}
