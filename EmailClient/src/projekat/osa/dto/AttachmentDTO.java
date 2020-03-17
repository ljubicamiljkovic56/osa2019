package projekat.osa.dto;

import java.io.Serializable;
import java.util.Base64;

import projekat.osa.entity.Attachment;

public class AttachmentDTO implements Serializable {

    private Integer id;
    private byte[] data;
    private String mime_type;
    private String name;


    public AttachmentDTO() {
    }

    public AttachmentDTO(Integer id, byte[] data, String mime_type, String name) {
        this.id = id;
        this.data = data;
        this.mime_type = mime_type;
        this.name = name;
    }
    
    public AttachmentDTO(Attachment a) {
        this.id = a.getId();
        this.data = Base64.getEncoder().encode(a.getData().getBytes());
        this.mime_type = a.getMimeType();
        this.name = a.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getType() {
        return mime_type;
    }

    public void setType(String type) {
        this.mime_type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}


