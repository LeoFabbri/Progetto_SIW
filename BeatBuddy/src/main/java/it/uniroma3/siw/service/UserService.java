package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        return this.userRepository.findById(id).get();
    }

    public Iterable<User> findAll(Long id){
        return this.userRepository.findAll();
    }

    public void save(User user){
        this.userRepository.save(user);
    }

}
