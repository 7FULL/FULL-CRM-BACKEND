package com.example.crm_backend.controllersTest;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

import com.example.crm_backend.controllers.EmployeeController;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestEmployeeController {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testLoginEndpoint() throws Exception {
        when(employeeService.login("admin", "admin")).thenReturn(new Employee());

        mockMvc.perform(post("/api/employee/login")
                        .param("username", "admin")
                        .param("password", "admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").exists());

        verify(employeeService, times(1)).login("admin", "admin");
    }

    @Test
    public void testPinPon() throws Exception {
        mockMvc.perform(get("/api/employee/pin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value("pon"));
    }

    @Test
    public void testGetExample() throws Exception {
        when(employeeService.getExampleEmployee()).thenReturn(new Employee());

        mockMvc.perform(get("/api/employee/getExample"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").exists());

        verify(employeeService, times(1)).getExampleEmployee();
    }

    @Test
    public void testForgotPassword() throws Exception {
        when(employeeService.getEmployeeByEmail("admin")).thenReturn(new Employee());

        mockMvc.perform(post("/api/employee/forgotPassword")
                        .param("email", "admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").exists());

        verify(employeeService, times(1)).getEmployeeByEmail("admin");
    }

    @Test
    public void testNewPassword() throws Exception {
        when(employeeService.getEmployeeByToken("test")).thenReturn(new Employee());

        mockMvc.perform(put("/api/employee/newPassword")
                        .param("token", "test")
                        .param("password", "admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").exists());

        verify(employeeService, times(1)).getEmployeeByToken("test");
    }

    @Test
    public void testCheckToken() throws Exception {
        when(employeeService.getEmployeeByToken("test")).thenReturn(new Employee());

        mockMvc.perform(post("/api/employee/checkToken")
                        .param("token", "test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").exists());

        verify(employeeService).getEmployeeByToken("test");
    }
}
