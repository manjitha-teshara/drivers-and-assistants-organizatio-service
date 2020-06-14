package com.driversandassistantsorganizationapp.demo.services;

import com.driversandassistantsorganizationapp.demo.models.Role;
import com.driversandassistantsorganizationapp.demo.repositories.RoleRepositorie;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private RoleRepositorie roleRepositorie;

    @Contract(pure = true)
    @Autowired
    public RoleService(RoleRepositorie roleRepositorie) {
        this.roleRepositorie = roleRepositorie;
    }

    //get all roles
    public List<Role> getAllRoles() {
        List<Role> roleList = new ArrayList<>();
        roleRepositorie.findAll().forEach(roleList::add);
        return roleList;
    }

    //add or update role
    public Role addOrUpdate(Role role) {
               return roleRepositorie.save(role);
    }

    //delete a role by nic no
    public void deleteRole(String nicNo) {
        roleRepositorie.deleteByNic(nicNo);
    }

    //get role by nic no
    public Role getRoleByNic(String nicNo) {
        List<Role> roleList = roleRepositorie.getByNic(nicNo);
        if(roleList.isEmpty()){
            return new Role();
        }
        else {
            return roleRepositorie.getByNic(nicNo).get(0);
        }
    }

    //get role by organization and role type
    public List<Role> findByOrganizationAndRoleType(String organization, String roleType) {
        return roleRepositorie.getByOrganizationAndRoleType(organization, roleType);
    }

    //check avaliability by nic no
    public boolean findByNic(String nicNo) {
        List<Role> roleList = roleRepositorie.getByNic(nicNo);
        return !roleList.isEmpty();

    }
}
