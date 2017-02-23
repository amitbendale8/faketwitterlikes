package com.demo.entity.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Component;

import com.demo.entity.User;
@EnableSpringDataWebSupport
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<User, String> {
	@Query("{ 'email' : ?0 }")
    List<User> findByEmail(String email);
    
    List<User> findByName(String name);
}
