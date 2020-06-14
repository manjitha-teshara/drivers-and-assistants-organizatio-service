package com.driversandassistantsorganizationapp.demo.controller;

import com.driversandassistantsorganizationapp.demo.controllers.RoleController;
import com.driversandassistantsorganizationapp.demo.model_dtos.role.RoleGetDTO;
import com.driversandassistantsorganizationapp.demo.model_dtos.role.RolePostDTO;
import com.driversandassistantsorganizationapp.demo.model_dtos.role.RolePutDTO;
import com.driversandassistantsorganizationapp.demo.models.Role;
import com.driversandassistantsorganizationapp.demo.services.RoleService;
import com.driversandassistantsorganizationapp.demo.utils.CustomResponse;
import com.driversandassistantsorganizationapp.demo.utils.EntityDtoMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class RoleControllerTest {
    private static final Date DATE = new Date();
    private static final  String ORG1 = "lamborghini";
    private static final String FIRSTNAME = "manjitha";
    private static final String LASTNAME = "teshara";
    private static final String NICNO = "123456789v";
    private static final String DRIVER = "driver";
    private static final String ASSISTANT = "assistant";
    private static final String INVALIDNICMSG = "invalid nic format";

    RoleController roleController;
    RoleService roleServiceMock;

    @BeforeEach
    void setUp() {
        roleServiceMock = Mockito.mock(RoleService.class);
        roleController = new RoleController(roleServiceMock);
    }

    @Test
    void testGetAllRoles_roleList_return() {

        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role());
        Mockito.when(roleServiceMock.getAllRoles()).thenReturn(roleList);
        List<RoleGetDTO> roleGetDTOList = new ArrayList<>();
        roleList.forEach(role -> {
            RoleGetDTO roleGetDTO = (RoleGetDTO) EntityDtoMapping.convertDtoToEntity(role, RoleGetDTO.class);
            roleGetDTOList.add(roleGetDTO);
        });
        ResponseEntity<Object> actual= roleController.getAllRoles();
        ResponseEntity<Object> expected = CustomResponse.successResponse(roleGetDTOList);
        assertEquals(actual,expected);
    }

    //addRole function test
    @Test
    void testAddRole_isEmpty_rolePostDTO() {
        assertEquals(roleController.addRole(new RolePostDTO()),CustomResponse.badRequestResponse(null));
    }
    @Test
    void testAddRole_isEmpty_organization() {
        RolePostDTO role =new RolePostDTO();
        role.setFirstName(FIRSTNAME);
        role.setLastName(LASTNAME);
        role.setNicNo(NICNO);
        role.setRoleType(DRIVER);
        role.setCreatedDate(DATE);
        role.setLastModifiedDate(DATE);
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(null));
    }
    @Test
    void testAddRole_isEmpty_firstName() {
        RolePostDTO role =new RolePostDTO();
        role.setOrganization(ORG1);
        role.setLastName(FIRSTNAME);
        role.setNicNo(NICNO);
        role.setRoleType(DRIVER);
        role.setCreatedDate(DATE);
        role.setLastModifiedDate(DATE);
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(null));
    }
    @Test
    void testAddRole_isEmpty_lastName() {
        RolePostDTO role =new RolePostDTO();
        role.setOrganization(ORG1);
        role.setFirstName(FIRSTNAME);
        role.setNicNo(NICNO);
        role.setRoleType(DRIVER);
        role.setCreatedDate(DATE);
        role.setLastModifiedDate(DATE);
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(null));
    }
    @Test
    void testAddRole_isEmpty_nicNo() {
        RolePostDTO role =new RolePostDTO();
        role.setOrganization(ORG1);
        role.setFirstName(FIRSTNAME);
        role.setLastName(LASTNAME);
        role.setRoleType(DRIVER);
        role.setCreatedDate(DATE);
        role.setLastModifiedDate(DATE);
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(null));
    }
    @Test
    void testAddRole_inValid_nic_format() {
        RolePostDTO role =new RolePostDTO();
        role.setOrganization(ORG1);
        role.setFirstName(FIRSTNAME);
        role.setLastName(LASTNAME);
        role.setRoleType(DRIVER);
        role.setCreatedDate(DATE);
        role.setLastModifiedDate(DATE);
        role.setNicNo("12345678v");//les than 9 numbers for old nic card number in sri lanka
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(INVALIDNICMSG));
        role.setNicNo("1234567891v");//more than 9 numbers for old nic card number in sri lanka
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(INVALIDNICMSG));
        role.setNicNo("1234x5678v");//9 numbers include charector for old nic card number in sri lanka
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(INVALIDNICMSG));
        role.setNicNo("123456789x");//v replace with another charector for old nic card number in sri lanka
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(INVALIDNICMSG));

        role.setNicNo("12345678912");//les than 12 numbers for new nic card number in sri lanka
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(INVALIDNICMSG));
        role.setNicNo("1234567891234");//more than 12 numbers for new nic card number in sri lanka
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(INVALIDNICMSG));
        role.setNicNo("12345x678912");//include charector in 12 numbers for new nic card number in sri lanka
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse(INVALIDNICMSG));

    }
    @Test
    void testAddRole_roleType_mismatch() {
        RolePostDTO role =new RolePostDTO();
        role.setOrganization(ORG1);
        role.setFirstName(FIRSTNAME);
        role.setLastName(LASTNAME);
        role.setNicNo(NICNO);
        role.setCreatedDate(DATE);
        role.setLastModifiedDate(DATE);

        role.setRoleType("student");
        assertEquals(roleController.addRole(role),CustomResponse.badRequestResponse("role type mismatch"));
    }




}
