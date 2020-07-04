package com.driversandassistantsorganizationapp.demo.services;

import com.driversandassistantsorganizationapp.demo.model_dtos.role.RoleGetDTO;
import com.driversandassistantsorganizationapp.demo.models.Role;
import com.driversandassistantsorganizationapp.demo.repositories.RoleRepositorie;
import com.driversandassistantsorganizationapp.demo.utils.EntityDtoMapping;
import org.jetbrains.annotations.Contract;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class RoleService {

    private RoleRepositorie roleRepositorie;


    @Contract(pure = true)
    @Autowired
    public RoleService(RoleRepositorie roleRepositorie) {
        this.roleRepositorie = roleRepositorie;
    }

    //get all roles
    public List<RoleGetDTO> getAllRoles() throws Exception {
        List<RoleGetDTO> roleGetDTOList = new ArrayList<>();
        roleRepositorie.findAll().forEach(role -> {
            RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertEntityToDto(role, RoleGetDTO.class);
            roleGetDTOList.add(roleGetDTO);
        });
        return roleGetDTOList;
    }


    //add or update role
    public RoleGetDTO addOrUpdate(Role role) throws Exception {
        RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertEntityToDto(role, RoleGetDTO.class);
//        //role validate nic no
//        if (!role.getNicNo().isEmpty()) {
//            if (this.findByNic(role.getNicNo())) {
//                System.out.println("role exist for nic no");
////                return role;
//            }
//            boolean isMatched = Pattern.matches("^([0-9]{9})+[vV]$|^[0-9]{12}$", role.getNicNo());
//            if (!isMatched) {
//                System.out.println("invalid nic format");
////                return role;
//            }
//        }
//        //role validate role type
//        if (!role.getRoleType().isEmpty()) {
//            if (!role.getRoleType().toLowerCase().equals("driver") && !role.getRoleType().toLowerCase().equals("assistant")) {
//                System.out.println("role type mismatch");
////                return role;
//            }
//        }
        // Instantiate a Date
        Date date = new Date();
        if (role.getCreatedDate() == null) {
            role.setCreatedDate(date);
        }
        role.setLastModifiedDate(date);
        Role roleStored = roleRepositorie.save(role);
        roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertEntityToDto(roleStored, RoleGetDTO.class);
        return roleGetDTO;
    }

    //delete a role by nic no
    public void deleteRole(String nicNo) {
        roleRepositorie.deleteByNic(nicNo);
    }

    //get role by nic no
    public Role getRoleByNic(String nicNo) {
        Role role = roleRepositorie.getByNic(nicNo);
        return role;
    }

    //get role by organization and role type
    public List<RoleGetDTO> findByOrganizationAndRoleType(String organization, String roleType) {
        List<Role> roleList = roleRepositorie.getByOrganizationAndRoleType(organization, roleType);
        List<RoleGetDTO> roleGetDTOList = new ArrayList<>();
        roleList.forEach(role -> {
            RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertEntityToDto(role, RoleGetDTO.class);
            roleGetDTOList.add(roleGetDTO);
        });
        return roleGetDTOList;
    }

    //check avaliability by nic no
    public boolean findByNic(String nicNo) {
        Role role = roleRepositorie.getByNic(nicNo);
        return !role.getId().isEmpty();
    }
}
