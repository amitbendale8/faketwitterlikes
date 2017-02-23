package com.demo.entity.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demo.entity.Provider;

@RepositoryRestResource(collectionResourceRel = "providers", path = "providers")
public interface ProviderRepository extends MongoRepository<Provider, String> {

}
