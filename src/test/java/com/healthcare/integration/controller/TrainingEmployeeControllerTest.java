package com.healthcare.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.model.entity.TrainingEmployee;
import com.healthcare.service.TrainingEmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jean on 03/07/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
public class TrainingEmployeeControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private TrainingEmployeeService trainingEmployeeService;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testSaveTrainingEmployee() throws Exception {
        TrainingEmployee trainingEmployee = new TrainingEmployee();
        Mockito.when(trainingEmployeeService.save(trainingEmployee)).thenReturn(trainingEmployee);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(trainingEmployee);
        this.mockMvc.perform(post("/api/training_has_employee").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTrainingEmployee() throws Exception {
        Mockito.when(trainingEmployeeService.findById(1L)).thenReturn(new TrainingEmployee());
        this.mockMvc.perform(get("/api/training_has_employee/")).andExpect(status().isOk());
    }

    @Test
    public void testFindAllTrainingEmployee() throws Exception {
        Mockito.when(trainingEmployeeService.findAll()).thenReturn(new ArrayList<TrainingEmployee>());
        this.mockMvc.perform(get("/api/training_has_employee")).andExpect(status().isOk());
    }

    @Test
    public void testUpdateTrainingEmployee() throws Exception {
        TrainingEmployee trainingEmployee = new TrainingEmployee();
        Mockito.when(trainingEmployeeService.save(trainingEmployee)).thenReturn(trainingEmployee);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(trainingEmployee);
        this.mockMvc.perform(put("/api/training_has_employee").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteTrainingEmployee() throws Exception {
        Mockito.when(trainingEmployeeService.deleteById(1L)).thenReturn(1L);
        this.mockMvc.perform(delete("/api/training_has_employee/1")).andExpect(status().isOk());
    }
}

