package com.driversandassistantsorganizationapp.demo.controllers;

import com.driversandassistantsorganizationapp.demo.model_dtos.role.RoleGetDTO;
import com.driversandassistantsorganizationapp.demo.model_dtos.role.RolePostDTO;
import com.driversandassistantsorganizationapp.demo.model_dtos.role.RolePutDTO;
import com.driversandassistantsorganizationapp.demo.models.Role;
import com.driversandassistantsorganizationapp.demo.services.RoleService;
import com.driversandassistantsorganizationapp.demo.utils.CustomResponse;
import com.driversandassistantsorganizationapp.demo.utils.EntityDtoMapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            List<RoleGetDTO> roleGetDTOList = roleService.getAllRoles();

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
            RoleGetDTO roleGetDTO = roleService.addOrUpdate(role);
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
            Role role = (Role) EntityDtoMapping.convertDtoToEntity(rolePutDTO, Role.class);
            //role validate nic no
            if (!rolePutDTO.getNicNo().isEmpty()) {
                boolean isMatched = Pattern.matches("^([0-9]{9})+[vV]$|^[0-9]{12}$", rolePutDTO.getNicNo());
                if (!isMatched) {
                    return CustomResponse.badRequestResponse("invalid nic format");
                }
            }
            //role validate role type
            if (!rolePutDTO.getRoleType().isEmpty()) {
                if (!rolePutDTO.getRoleType().toLowerCase().equals("driver") && !rolePutDTO.getRoleType().toLowerCase().equals("assistant")) {
                    return CustomResponse.badRequestResponse("role type mismatch");
                }
            }
            Role roleByNic = roleService.getRoleByNic(nicNo);
            role.setCreatedDate(roleByNic.getCreatedDate());
            role.setId(roleByNic.getId());
            // Instantiate a Date object
            Date date = new Date();
            role.setLastModifiedDate(date);
            RoleGetDTO roleUpdated = roleService.addOrUpdate(role);
            return CustomResponse.successResponse(roleUpdated);

        } catch (Exception e) {
            return CustomResponse.badRequestResponse(null);
        }
    }

    @GetMapping("/get-role/{nicNo}")
    public ResponseEntity<Object> getRoleByNic(@PathVariable String nicNo) {
        try {
            Role role = roleService.getRoleByNic(nicNo);
            return CustomResponse.successResponse(EntityDtoMapping.convertDtoToEntity(role, RoleGetDTO.class));
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }

    @GetMapping("/get-role/{organization}/{roleType}")
    public ResponseEntity<Object> getByOrganizationAndRoleType(@PathVariable String organization, @PathVariable String roleType) {
        try {
            return CustomResponse.successResponse(roleService.findByOrganizationAndRoleType(organization, roleType));
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }
}
