package com.ipf.iems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipf.iems.entity.Employee;
import com.ipf.iems.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        employee = createEmployee("John", "Doe", "john.doe@example.com", "Engineering", 5000.0);
        employeeRepository.save(employee);
    }

    // Utility method to create an employee
    private Employee createEmployee(String firstName, String lastName, String email, String department, double salary) {
        Employee emp = new Employee();
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setEmail(email);
        emp.setDepartment(department);
        emp.setSalary(salary);
        return emp;
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        Employee newEmployee = createEmployee("Jane", "Smith", "jane.smith@example.com", "HR", 6000.0);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Jane"));
    }

    @Test
    void shouldGetAllEmployeesWithPagination() throws Exception {
        // Add more employees for pagination
        employeeRepository.save(createEmployee("Jane", "Smith", "jane.smith@example.com", "HR", 6000.0));
        employeeRepository.save(createEmployee("Michael", "Brown", "michael.brown@example.com", "Finance", 7000.0));

        mockMvc.perform(get("/api/employees")
                        .param("page", "0")  // Request first page
                        .param("size", "2")) // Request 2 employees per page
                .andExpect(status().isOk()) // Expect HTTP 200 status
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2))  // Ensure 2 employees per page
                .andExpect(jsonPath("$.content[0].firstName").value("John"))  // Check first employee's first name
                .andExpect(jsonPath("$.content[1].firstName").value("Jane"))  // Check second employee's first name
                .andExpect(jsonPath("$.totalElements").value(3))  // Ensure total employees are 3
                .andExpect(jsonPath("$.totalPages").value(2));  // Ensure total pages is 2
    }

    @Test
    void shouldGetEmployeeById() throws Exception {
        mockMvc.perform(get("/api/employees/{id}", employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void shouldUpdateEmployee() throws Exception {
        employee.setDepartment("Finance");

        mockMvc.perform(put("/api/employees/{id}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.department").value("Finance"));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/{id}", employee.getId()))
                .andExpect(status().isNoContent());
    }
}
