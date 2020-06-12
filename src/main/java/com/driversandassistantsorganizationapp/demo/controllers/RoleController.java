package com.driversandassistantsorganizationapp.demo.controllers;

import com.driversandassistantsorganizationapp.demo.model_dtos.role.RoleGetDTO;
import com.driversandassistantsorganizationapp.demo.model_dtos.role.RolePostDTO;
import com.driversandassistantsorganizationapp.demo.models.Role;
import com.driversandassistantsorganizationapp.demo.services.RoleService;
import com.driversandassistantsorganizationapp.demo.utils.CustomResponse;
import com.driversandassistantsorganizationapp.demo.utils.EntityDtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            // rolePostDTO to role
            Role role = (Role) EntityDtoMapping.convertDtoToEntity(rolePostDTO, Role.class);
            Role roleStored = roleService.addOrUpdate(role);
            //convert role to dto
            RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertDtoToEntity(role, RoleGetDTO.class);
            return CustomResponse.successResponse(roleGetDTO);
        } catch (Exception e) {
            return CustomResponse.badRequestResponse(e.getMessage());
        }
    }




}
