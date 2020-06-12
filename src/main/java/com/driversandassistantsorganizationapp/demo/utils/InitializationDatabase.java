package com.driversandassistantsorganizationapp.demo.utils;

import com.driversandassistantsorganizationapp.demo.models.Role;
import com.driversandassistantsorganizationapp.demo.repositories.RoleRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializationDatabase {

    @Autowired
    private RoleRepositorie roleRepositorie;

    @PostConstruct
    private void init() {
        roleRepositorie.deleteAll();
// drivers
        Role role = new Role();
        role.setOrganization("lamborghini");
        role.setFirstName("Dilanth");
        role.setLastName("malagamuwa");
        role.setNicNo("632401100v");
        role.setRoleType("driver");
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("lamborghini");
        role.setFirstName("Dilanth");
        role.setLastName("malagamuwa");
        role.setNicNo("632401200v");
        role.setRoleType("driver");
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("lamborghini");
        role.setFirstName("dilanth");
        role.setLastName("malagamuwa");
        role.setNicNo("632401300v");
        role.setRoleType("driver");
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("mercedes benz");
        role.setFirstName("ayrton");
        role.setLastName("senna");
        role.setNicNo("782401400v");
        role.setRoleType("driver");
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("lamborghini");
        role.setFirstName("michael");
        role.setLastName("schumacher");
        role.setNicNo("702401500v");
        role.setRoleType("driver");
        roleRepositorie.save(role);
// assistant
        role = new Role();
        role.setOrganization("ferrari");
        role.setFirstName("taye");
        role.setLastName("hamilton");
        role.setNicNo("502401600v");
        role.setRoleType("assistant");
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("lamborghini");
        role.setFirstName("kumara");
        role.setLastName("malagamuwa");
        role.setNicNo("702401700v");
        role.setRoleType("assistant");
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("lamborghini");
        role.setFirstName("michael");
        role.setLastName("mask");
        role.setNicNo("902401800v");
        role.setRoleType("assistant");
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("ferrari");
        role.setFirstName("lewis");
        role.setLastName("barack");
        role.setNicNo("402401900v");
        role.setRoleType("assistant");
        roleRepositorie.save(role);

        role = new Role();
        role.setOrganization("ford");
        role.setFirstName("sadun");
        role.setLastName("gamage");
        role.setNicNo("302401100v");
        role.setRoleType("assistant");
        roleRepositorie.save(role);

    }
}

