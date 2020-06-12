package com.driversandassistantsorganizationapp.demo.services;

import com.driversandassistantsorganizationapp.demo.models.Role;
import com.driversandassistantsorganizationapp.demo.repositories.RoleRepositorie;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private RoleRepositorie roleRepositorie;

    @Contract(pure = true)
    @Autowired
    public RoleService(RoleRepositorie roleRepositorie) { this.roleRepositorie = roleRepositorie;}

    //get all roles
    public List<Role> getAllRoles() {
        List<Role> roleList = new ArrayList<>();
        roleRepositorie.findAll().forEach(roleList::add);
        return roleList;
    }

    //add or update role
    public Role addOrUpdate(Role role) { return roleRepositorie.save(role);}

    //check the availability

    //delete a role by id

    //get role by organization
    //get role by role type
    //get role by organization and role type
//    public List<Role> findByOrganizationAndRoletype(String organization, String roleType) {
//        return roleRepositorie.
//    }
}
