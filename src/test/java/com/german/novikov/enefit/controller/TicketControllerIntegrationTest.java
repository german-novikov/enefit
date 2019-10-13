package com.german.novikov.enefit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.german.novikov.enefit.EnefitApplication;
import com.german.novikov.enefit.exception.ApplicationException;
import com.german.novikov.enefit.model.Ticket;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = EnefitApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb"
})
public class TicketControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    private static final String TICKET_ENDPOINT = "/api/ticket";

    @Test
    public void createNewTicketRequest_shouldAddNewTicket_toTicketList()
            throws Exception {
        Ticket newTicket = new Ticket("Test", "testEmail@gmail.com", "ControllerTest", 1, 1);

        mvc.perform(put(TICKET_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTicket)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",not(0)));
    }

    @Test
    public void getTicketByIdRequest_shouldReturn_rightName_forTicket()
            throws Exception {
        Ticket newTicket = new Ticket("Test", "testEmail@gmail.com", "ControllerTest", 1, 1);

        mvc.perform(put(TICKET_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTicket)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        mvc.perform(get(TICKET_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id","1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Test")));
    }



    @Test
    public void updateTicketRequest_shouldUpdateTicketDescription()
            throws Exception {
        Ticket newTicket = new Ticket("Test", "testEmail@gmail.com", "ControllerTest", 1, 1);
        MvcResult result = mvc.perform(put(TICKET_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTicket)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        Long newTicketId = new Long(Arrays.asList(
                Arrays.asList(
                        result.getResponse().getContentAsString().split(","))
                        .get(0).split(":"))
                .get(1));
        newTicket.setId(newTicketId);
        newTicket.setDescription("Updated description");

        mvc.perform(post(TICKET_ENDPOINT + "/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTicket)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(new Integer(newTicketId.toString()))))
                .andExpect(jsonPath("$.description", is("Updated description")));
    }

    @Test
    public void deleteTicketRequest_shouldDeleteTicket_fromTicketList()
            throws Exception {

        Ticket newTicket = new Ticket("Test", "testEmail@gmail.com", "ControllerTest", 1, 1);
        MvcResult result = mvc.perform(put(TICKET_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTicket)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        Long newTicketId = new Long(Arrays.asList(
                Arrays.asList(
                        result.getResponse().getContentAsString().split(","))
                        .get(0).split(":"))
                .get(1));

        mvc.perform(get(TICKET_ENDPOINT + "/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", newTicketId.toString()))
                .andExpect(status().isOk());

        assertThatThrownBy(() -> mvc.perform(get(TICKET_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id",newTicketId.toString()))
                .andExpect(status().isOk()))
                .hasCause(new ApplicationException(String.format("Ticket with id %s not exist", newTicketId)));
    }

    @Test
    public void deleteTaskRequest_shouldDeleteTask_FromTheList()
            throws Exception {

        mvc.perform(get(TICKET_ENDPOINT + "/priority")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.0", is("Lowest")));

    }
}
