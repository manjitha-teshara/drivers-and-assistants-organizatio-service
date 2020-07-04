package com.driversandassistantsorganizationapp.demo.services;

import com.driversandassistantsorganizationapp.demo.repositories.RoleRepositorie;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceTest {
RoleService roleService = Mockito.mock(RoleService.class);
RoleRepositorie roleRepositorie = Mockito.mock(RoleRepositorie.class);
    @Test
    void getAllRoles() throws Exception {
    }

    @Test
    void addOrUpdate() {
    }

    @Test
    void deleteRole() {
    }

    @Test
    void getRoleByNic() {
    }

    @Test
    void findByOrganizationAndRoleType() {
    }

    @Test
    void findByNic() {
    }
}