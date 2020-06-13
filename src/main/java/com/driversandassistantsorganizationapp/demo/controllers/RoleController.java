package com.driversandassistantsorganizationapp.demo.controllers;

import com.driversandassistantsorganizationapp.demo.model_dtos.role.RoleGetDTO;
import com.driversandassistantsorganizationapp.demo.model_dtos.role.RolePostDTO;
import com.driversandassistantsorganizationapp.demo.model_dtos.role.RolePutDTO;
import com.driversandassistantsorganizationapp.demo.models.Role;
import com.driversandassistantsorganizationapp.demo.services.RoleService;
import com.driversandassistantsorganizationapp.demo.utils.CustomResponse;
import com.driversandassistantsorganizationapp.demo.utils.EntityDtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllRoles() {
        try{
            List<Role> roleList = roleService.getAllRoles();
            List<RoleGetDTO> roleGetDTOList = new ArrayList<>();
            roleList.forEach(role -> {
                RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertDtoToEntity(role, RoleGetDTO.class);
                roleGetDTOList.add(roleGetDTO);
            });
            return CustomResponse.successResponse(roleGetDTOList);
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }

    @PostMapping(path = "/add")
    @ResponseBody
    public ResponseEntity addRole(@RequestBody RolePostDTO rolePostDTO) {
        try {
            if(roleService.findByNicNo(rolePostDTO.getNicNo())){
                return CustomResponse.notFoundResponse("role exist for given nic no");
            }
            // rolePostDTO to role
            Role role = (Role) EntityDtoMapping.convertDtoToEntity(rolePostDTO, Role.class);
            // Instantiate a Date object
            Date date = new Date();
            role.setCreatedDate(date);
            role.setLastModifiedDate(date);
            Role roleStored = roleService.addOrUpdate(role);
            //convert role to dto
            RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertDtoToEntity(role, RoleGetDTO.class);
            return CustomResponse.successResponse(roleGetDTO);
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{nicNo}")
    public ResponseEntity deleteRole(@PathVariable String nicNo) {
        try {
            if(!roleService.findByNicNo(nicNo)){
                return CustomResponse.notFoundResponse("role not found for given nic no");
            }
            roleService.deleteRole(nicNo);
            return CustomResponse.successResponse(null);
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }

    @PutMapping("/update/{nicNo}")
    public ResponseEntity updateRole(@PathVariable String nicNo, @RequestBody RolePutDTO rolePutDTO){
        try {
            if(!roleService.findByNicNo(nicNo)){
                return CustomResponse.notFoundResponse("role not found for given nic no");
            }
            Role role = (Role) EntityDtoMapping.convertEntityToDto(rolePutDTO, Role.class);
            Role roleByNic = roleService.getRoleByNic(nicNo);
            if(role.getFirstName().isEmpty()) {
                role.setFirstName(roleByNic.getFirstName());
            }
            if(role.getLastName().isEmpty()) {
                role.setLastName(roleByNic.getLastName());
            }
            if(role.getRoleType().isEmpty()) {
                role.setRoleType(roleByNic.getRoleType());
            }
            role.setNicNo(roleByNic.getNicNo());
            role.setCreatedDate(roleByNic.getCreatedDate());
            // Instantiate a Date object
            Date date = new Date();
            role.setLastModifiedDate(date);
            Role roleUpdated = roleService.addOrUpdate(role);
            RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertDtoToEntity(roleUpdated, RoleGetDTO.class);
            return CustomResponse.successResponse(roleGetDTO);
        }catch (Exception e){
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }

    @GetMapping("/get-role/{nicNo}")
    public ResponseEntity getRoleByNic(@PathVariable String nicNo) {
        try{
            Role role = roleService.getRoleByNic(nicNo);
            RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertDtoToEntity(role, RoleGetDTO.class);
            return CustomResponse.successResponse(roleGetDTO);
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }

    @GetMapping("/get-role/{organization}/{roleType}")
    public ResponseEntity GetByOrganizationAndRoleType(@PathVariable String organization,@PathVariable String roleType) {
        try{
            List<Role> roleList = roleService.findByOrganizationAndRoleType(organization,roleType);
            List<RoleGetDTO> roleGetDTOList = new ArrayList<>();
            roleList.forEach(role -> {
                RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertDtoToEntity(role, RoleGetDTO.class);
                roleGetDTOList.add(roleGetDTO);
            });
            return CustomResponse.successResponse(roleGetDTOList);
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }
}
