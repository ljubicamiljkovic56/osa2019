package projekat.osa.service;

import java.util.List;

import projekat.osa.entity.Account;
import projekat.osa.entity.Folder;

public interface FolderServiceInterface {

	List<Folder> findByParent(Folder parentFolder);
	
	Folder findOne(long folderId);
	
	List<Folder> findAll();
	
	Folder save(Folder folder);
	
	void remove(Integer id);
	
	Folder findByName(String name);
	
	Folder findByNameAndAccount(String name, Account account);

}
