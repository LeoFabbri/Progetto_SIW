package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;

@Service
public class CredentialsService {
    
    @Autowired
    private CredentialsRepository credentialsRepository;

    public Credentials findById(Long id){
        return this.credentialsRepository.findById(id).get();
    }

    public Credentials findByUsername(String username){
        return this.credentialsRepository.findByUsername(username).get();
    }

    public void save(Credentials Credentials){
        this.credentialsRepository.save(Credentials);
    }

}
