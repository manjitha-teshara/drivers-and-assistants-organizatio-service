package com.driversandassistantsorganizationapp.demo.repositories;

import com.driversandassistantsorganizationapp.demo.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "role", path = "role")

public interface RoleRepositorie extends MongoRepository<Role, String> {
    @Query(value = "{ 'nicNo': ?0 }") //, fields = "{ 'firstName' : 1,'lastName' : 1,'nicNo' : 1}"
    Role getByNic(String nic);

    @Query(value = "{ $and: [{'organization': ?0}, {'roleType': ?1 }] }")//, fields = "{ 'firstName' : 1,'lastName' : 1,'nicNo' : 1}")
    List<Role> getByOrganizationAndRoleType(String organization, String roleType);

    @Query(value = "{'nicNo': ?0}", delete = true)
    void deleteByNic(String nicNo);

}
