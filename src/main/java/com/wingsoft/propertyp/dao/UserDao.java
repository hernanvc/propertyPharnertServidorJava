package com.wingsoft.propertyp.dao;



import com.wingsoft.propertyp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    public User findByEmailAndClave(String email, String Clase);

    public User findByEmail(String email);

    public User findByToken(String token);


    public boolean existsByEmail(String email);

}