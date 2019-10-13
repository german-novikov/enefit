package com.german.novikov.enefit.service;

import com.german.novikov.enefit.EnefitApplication;
import com.german.novikov.enefit.model.Ticket;
import com.german.novikov.enefit.repository.TicketRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EnefitApplication.class)
public class TicketServiceIntegrationTest {

    @Autowired
    TicketService ticketService;

    @MockBean
    private TicketRepository ticketRepository;


    @Before
    public void setUp() {
        Ticket newTicket = new Ticket("Test", "testEmail@gmail.com", "ControllerTest", 1, 1);
        Ticket newTicket1 = new Ticket("Test1", "testEmail@gmail.com", "ControllerTest", 0, 0);
        Ticket newTicket2 = new Ticket("Test2", "testEmail@gmail.com", "ControllerTest", 4, 1);

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(newTicket);
        tickets.add(newTicket1);
        tickets.add(newTicket2);
        Mockito.when(ticketRepository.findAll())
                .thenReturn(tickets);
    }

    @Test
    public void checkSortListByPriorityReturnCorrect(){
        List<Ticket> tickets = ticketService.sortTicketsByPriority("ASC");
        List<Integer> expectedOrder = Arrays.asList(0,1,4);
        List<Integer> actualOrder = new ArrayList<>();
        tickets.stream().forEach(ticket -> actualOrder.add(ticket.getPriorityLevel()));
        assertThat(actualOrder).isEqualTo(expectedOrder);

        tickets = ticketService.sortTicketsByPriority("DESC");
        expectedOrder = Arrays.asList(4,1,0);
        List<Integer> actualOrderByDESC = new ArrayList<>();
        tickets.stream().forEach(ticket -> actualOrderByDESC.add(ticket.getPriorityLevel()));
        assertThat(actualOrderByDESC).isEqualTo(expectedOrder);
    }

    @Test
    public void checkSortListByStatusReturnCorrect(){
        List<Ticket> tickets = ticketService.sortTicketsByStatus("ASC");
        List<Integer> expectedOrder = Arrays.asList(0,1,1);
        List<Integer> actualOrder = new ArrayList<>();
        tickets.stream().forEach(ticket -> actualOrder.add(ticket.getStatusId()));
        assertThat(actualOrder).isEqualTo(expectedOrder);

        tickets = ticketService.sortTicketsByStatus("DESC");
        expectedOrder = Arrays.asList(1,1,0);
        List<Integer> actualOrderByDESC = new ArrayList<>();
        tickets.stream().forEach(ticket -> actualOrderByDESC.add(ticket.getStatusId()));
        assertThat(actualOrderByDESC).isEqualTo(expectedOrder);
    }

    @Test
    public void testGetPriorityList(){
        Map<Integer, String> priorities = ticketService.getPriorityList();
        assertThat(priorities.size()).isEqualTo(5);
    }

}
