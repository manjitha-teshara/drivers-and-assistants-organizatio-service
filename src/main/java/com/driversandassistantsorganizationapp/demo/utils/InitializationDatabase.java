package com.driversandassistantsorganizationapp.demo.utils;

import com.driversandassistantsorganizationapp.demo.models.Role;
import com.driversandassistantsorganizationapp.demo.repositories.RoleRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class InitializationDatabase {

    private static final  String ORG1 = "lamborghini";
    private static final String DRIVER = "driver";
    private static final String ASSISTANT = "assistant";
    @Autowired
    private RoleRepositorie roleRepositorie;

    @PostConstruct
    private void init() {
        roleRepositorie.deleteAll();
// drivers
        Role role = new Role();
        Date date = new Date();


        role.setOrganization(ORG1);
        role.setFirstName("dilanth");
        role.setLastName("sugathapala");
        role.setNicNo("632401100v");
        role.setRoleType(DRIVER);
        role.setCreatedDate(date);
        role.setLastModifiedDate(date);
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization(ORG1);
        role.setFirstName("dilanka");
        role.setLastName("gamlath");
        role.setNicNo("632401200v");
        role.setRoleType(DRIVER);
        role.setCreatedDate(date);
        role.setLastModifiedDate(date);
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization(ORG1);
        role.setFirstName("dilanth");
        role.setLastName("malagamuwa");
        role.setNicNo("632401300v");
        role.setRoleType(DRIVER);
        role.setCreatedDate(date);
        role.setLastModifiedDate(date);
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("mercedes benz");
        role.setFirstName("ayrton");
        role.setLastName("senna");
        role.setNicNo("782401400v");
        role.setRoleType(DRIVER);
        role.setCreatedDate(date);
        role.setLastModifiedDate(date);
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization(ORG1);
        role.setFirstName("michael");
        role.setLastName("schumacher");
        role.setNicNo("702401500v");
        role.setRoleType(DRIVER);
        role.setCreatedDate(date);
        role.setLastModifiedDate(date);
        roleRepositorie.save(role);
// assistant
        role = new Role();
        role.setOrganization("ferrari");
        role.setFirstName("taye");
        role.setLastName("hamilton");
        role.setNicNo("502401600v");
        role.setRoleType(ASSISTANT);
        role.setCreatedDate(date);
        role.setLastModifiedDate(date);
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization(ORG1);
        role.setFirstName("kumara");
        role.setLastName("malagamuwa");
        role.setNicNo("702401700v");
        role.setRoleType(ASSISTANT);
        role.setCreatedDate(date);
        role.setLastModifiedDate(date);
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization(ORG1);
        role.setFirstName("michael");
        role.setLastName("mask");
        role.setNicNo("902401800v");
        role.setRoleType(ASSISTANT);
        role.setCreatedDate(date);
        role.setLastModifiedDate(date);
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("ferrari");
        role.setFirstName("lewis");
        role.setLastName("barack");
        role.setNicNo("402401900v");
        role.setRoleType(ASSISTANT);
        role.setCreatedDate(date);
        role.setLastModifiedDate(date);
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("ford");
        role.setFirstName("sadun");
        role.setLastName("gamage");
        role.setNicNo("302401100v");
        role.setRoleType(ASSISTANT);
        role.setCreatedDate(date);
        role.setLastModifiedDate(date);
        roleRepositorie.save(role);

    }
}

