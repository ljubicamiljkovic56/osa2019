package projekat.osa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.osa.entity.Account;
import projekat.osa.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Integer>{

	List<Folder> findByParent(Folder parentFolder);
	
	Folder findByName(String name);
	
	Folder findByNameAndAccount(String name, Account account);
}
