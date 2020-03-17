package projekat.osa.dto;

import java.io.Serializable;

import projekat.osa.entity.Photo;

public class PhotoDTO implements Serializable {

    private Integer id;
    private String path;

    public PhotoDTO() {
    }

    public PhotoDTO(Integer id, String path) {
        this.id = id;
        this.path = path;
    }
    
    public PhotoDTO(Photo p) {
        this.id = p.getId();
        this.path = p.getPath();
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
}
