package projekat.osa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import projekat.osa.entity.User;
import projekat.osa.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByUsernameAndPassword(String username, String password){
		User user = userRepository.findByUsername(username);
		if(user != null && user.getPassword().equals(password))
			return user;
		else
			return null;
	}
	
	@Override
	public User findOne(Integer userId) {
		
		return userRepository.findOne(userId);
	}
	
	@Override
	public void add(User user){
		user = userRepository.findOne((int) user.getId());
		userRepository.save(user);
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
