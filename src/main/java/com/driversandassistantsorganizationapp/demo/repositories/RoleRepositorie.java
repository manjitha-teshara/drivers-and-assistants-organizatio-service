package com.driversandassistantsorganizationapp.demo.repositories;

import com.driversandassistantsorganizationapp.demo.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "role", path = "role")

public interface RoleRepositorie extends MongoRepository<Role, String> {
}
