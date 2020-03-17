package projekat.osa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.osa.entity.Account;
import projekat.osa.entity.Folder;
import projekat.osa.repository.FolderRepository;


@Service
public class FolderService implements FolderServiceInterface{

	@Autowired
	FolderRepository folderRepository;
	
	@Override
	public List<Folder> findByParent(Folder parentFolder){
		return folderRepository.findByParent(parentFolder);
	}
	
	@Override
	public Folder findOne(long folderId){
		return folderRepository.findOne((int) folderId);
	}

	@Override
	public List<Folder> findAll() {
		return folderRepository.findAll();
	}

	@Override
	public Folder save(Folder folder) {
		return folderRepository.save(folder);
	}

	@Override
	public void remove(Integer id) {
		folderRepository.delete(id);
	}
	
	@Override
	public Folder findByName(String name) {
		return folderRepository.findByName(name);
	}

	@Override
	public Folder findByNameAndAccount(String name, Account account) {
		return folderRepository.findByNameAndAccount(name, account);
	}
}
