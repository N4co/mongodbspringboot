package com.nicolasfelix.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicolasfelix.workshopmongo.domain.User;
import com.nicolasfelix.workshopmongo.dto.UserDTO;
import com.nicolasfelix.workshopmongo.repository.UserRepository;
import com.nicolasfelix.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
	return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
     
	public User Insert(User obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {	
		repo.deleteById(id);
	}	
	 
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail()); 
	}
    
    public void update(String obj) {
    	
    }
    }

