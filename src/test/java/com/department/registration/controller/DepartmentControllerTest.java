package com.department.registration.controller;

import com.department.registration.model.DepartmentEntity;
import com.department.registration.repository.DepartmentRepository;
import com.department.registration.request.DepartmentRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentControllerTest {

    @Autowired
    public WebApplicationContext context;
    @Autowired
    private DepartmentRepository repository;

    private MockMvc mvc;

    private final String URL = "http://localhost:8080/api/v1/departments";

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testRequestGetAll() throws Exception {
        this.mvc.perform(get(URL))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRequestFindById() throws Exception {
        this.mvc.perform(get(URL + "/find-department?id=1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRequestFindByIdReturnNotFound() throws Exception {
        this.mvc.perform(get(URL + "/find-department?id=2"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private DepartmentRequest createDataRequest() {
        return DepartmentRequest.builder()
            .name("Test")
            .local("Itaú")
            .city("São Paulo")
            .state("SP")
            .board("Tecnologia")
            .build();
    }

    private DepartmentEntity createDepartment() {
        return DepartmentEntity.builder()
            .id(100L)
            .name("Test")
            .local("Itaú")
            .city("São Paulo")
            .state("SP")
            .board("Tecnologia")
            .build();
    }
}
