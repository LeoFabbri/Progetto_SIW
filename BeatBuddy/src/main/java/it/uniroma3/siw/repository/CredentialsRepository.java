package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Credentials;
import java.util.List;
import java.util.Optional;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials,Long>{
    
    public Optional<Credentials> findByUsername(String username);

}
