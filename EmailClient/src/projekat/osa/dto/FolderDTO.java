package projekat.osa.dto;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;


import projekat.osa.entity.Folder;
import projekat.osa.entity.Message;
import projekat.osa.entity.Rule;

public class FolderDTO implements Serializable {

    private Integer id;
    private String name;
    private List<FolderDTO> subFolders;
    private List<RuleDTO> rules;
    private List<MessageDTO> messages;

    public FolderDTO(){

    }

    public FolderDTO(Integer id, String name, List<FolderDTO> subFolders, List<RuleDTO> rules, List<MessageDTO> messages) {
        this.id = id;
        this.name = name;
        this.subFolders = subFolders;
        this.rules = rules;
        this.messages = messages;
    }
    
    public FolderDTO(Folder f){
        this.id = f.getId();
        this.name = f.getName();
        
    	List<MessageDTO> messageDTOs = new ArrayList<>();
    	if (f.getMessages() != null) {
        	for (Message itMessage: f.getMessages()) {
    			messageDTOs.add(new MessageDTO(itMessage));
    		}
		}
    	this.messages = messageDTOs;
    	
    	List<RuleDTO> ruleDTOs= new ArrayList<>();
    	if (f.getRules() != null) {
    		for(Rule itRule : f.getRules()) {
    			ruleDTOs.add(new RuleDTO(itRule));
    		}
		}
    	this.rules = ruleDTOs;
    	
    	List<FolderDTO> folderDTOs = new ArrayList<>();
    	if (f.getSubfolders() != null) {
    		for (Folder itFolder : f.getSubfolders()) {
    			folderDTOs.add(new FolderDTO(itFolder));
    		}
    	}
    	
    	/*if (f.getSubfolders() != null && !f.getSubfolders().isEmpty()) {
        	for (Folder itFolder : f.getSubfolders()) {
        		List<MessageDTO> messageDTOs2 = new ArrayList<>();
        		if(itFolder.getMessages() != null && !itFolder.getMessages().isEmpty())
                	for (Message itMessage: itFolder.getMessages()) {
            			messageDTOs2.add(new MessageDTO(itMessage));
            		}
        		List<RuleDTO> ruleDTOs2 = new ArrayList<>();
            	for (Rule itRule: itFolder.getRules()) {
            		ruleDTOs2.add(new RuleDTO(itRule));
        		}
        		
    			FolderDTO itFolderDTO = new FolderDTO(itFolder.getId(), itFolder.getName(), new ArrayList<>(), ruleDTOs2, messageDTOs2);
    			folderDTOs.add(itFolderDTO);
    		}
		}*/
    	
    	this.subFolders = folderDTOs;
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

	public List<FolderDTO> getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(List<FolderDTO> subFolders) {
		this.subFolders = subFolders;
	}

	public List<RuleDTO> getRules() {
		return rules;
	}

	public void setRules(List<RuleDTO> rules) {
		this.rules = rules;
	}

	public List<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}

    
    
}
