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
import java.util.regex.Pattern;

@CrossOrigin
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    List<RoleGetDTO> roleGetDTOList;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllRoles() {
        try {
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
    public ResponseEntity<Object> addRole(@RequestBody RolePostDTO rolePostDTO) {
        try {
            // rolePostDTO to role
            Role role = (Role) EntityDtoMapping.convertDtoToEntity(rolePostDTO, Role.class);

            //role validate nic no
            if(!rolePostDTO.getNicNo().isEmpty()) {
                if(roleService.findByNic(rolePostDTO.getNicNo())){
                    return CustomResponse.badRequestResponse("role exist for nic no");
                }
                boolean isMatched = Pattern.matches("^([0-9]{9})+[vV]$|^[0-9]{12}$",rolePostDTO.getNicNo());
                if(!isMatched) {
                    return CustomResponse.badRequestResponse("invalid nic format");
                }
            }
            //role validate role type
            if(!rolePostDTO.getRoleType().isEmpty()){
                if(!rolePostDTO.getRoleType().toLowerCase().equals("driver") && !rolePostDTO.getRoleType().toLowerCase().equals("assistant") ) {
                    return CustomResponse.badRequestResponse("role type mismatch");
                }
            }

            // Instantiate a Date
            Date date = new Date();
            role.setCreatedDate(date);
            role.setLastModifiedDate(date);
            Role roleStored = roleService.addOrUpdate(role);
            //convert role to dto
            RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertDtoToEntity(roleStored, RoleGetDTO.class);
            return CustomResponse.successResponse(roleGetDTO);
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{nicNo}")
    public ResponseEntity<Object> deleteRole(@PathVariable String nicNo) {
        try {
            roleService.deleteRole(nicNo);
            return CustomResponse.successResponse("deleted");
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(null);
        }
    }

    @PutMapping("/update/{nicNo}")
    public ResponseEntity<Object> updateRole(@PathVariable String nicNo, @RequestBody RolePutDTO rolePutDTO) {
        try {
            Role role = (Role) EntityDtoMapping.convertEntityToDto(rolePutDTO, Role.class);
            //role validate nic no
            if(!rolePutDTO.getNicNo().isEmpty()) {
                boolean isMatched = Pattern.matches("^([0-9]{9})+[vV]$|^[0-9]{12}$",rolePutDTO.getNicNo());
                if(!isMatched) {
                    return CustomResponse.badRequestResponse("invalid nic format");
                }
            }
            //role validate role type
            if(!rolePutDTO.getRoleType().isEmpty()){
                if(!rolePutDTO.getRoleType().toLowerCase().equals("driver") && !rolePutDTO.getRoleType().toLowerCase().equals("assistant") ) {
                    return CustomResponse.badRequestResponse("role type mismatch");
                }
            }
            Role roleByNic = roleService.getRoleByNic(nicNo);
            role.setCreatedDate(roleByNic.getCreatedDate());
            role.setId(roleByNic.getId());
            // Instantiate a Date object
            Date date = new Date();
            role.setLastModifiedDate(date);
            Role roleUpdated = roleService.addOrUpdate(role);
            RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertDtoToEntity(roleUpdated, RoleGetDTO.class);
            return CustomResponse.successResponse(roleGetDTO);

        } catch (Exception e) {
            return CustomResponse.badRequestResponse(null);
        }
    }

    @GetMapping("/get-role/{nicNo}")
    public ResponseEntity<Object> getRoleByNic(@PathVariable String nicNo) {
        try {
            Role role = roleService.getRoleByNic(nicNo);
            RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertDtoToEntity(role, RoleGetDTO.class);
            return CustomResponse.successResponse(roleGetDTO);
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }

    @GetMapping("/get-role/{organization}/{roleType}")
    public ResponseEntity<Object> getByOrganizationAndRoleType(@PathVariable String organization, @PathVariable String roleType) {
        try {
            List<Role> roleList = roleService.findByOrganizationAndRoleType(organization, roleType);
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
