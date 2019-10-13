package com.german.novikov.enefit.database;

import com.german.novikov.enefit.EnefitApplication;
import com.german.novikov.enefit.model.Ticket;
import com.german.novikov.enefit.repository.TicketRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EnefitApplication.class)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb"
})
public class DatabaseTicketsTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void checkCreateNewTicketTest() {
        Ticket ticket = new Ticket();
        ticket.setTitle("TestTicket");
        ticket.setEmail("testEamil@gmail.com");
        ticket.setDescription("Ticket for test");
        ticket.setPriorityLevel(1);
        ticket.setStatusId(0);

        Ticket createdTicket = ticketRepository.save(ticket);
        assertThat(createdTicket).isNotNull();
        assertThat(createdTicket.getTitle()).isEqualTo(ticket.getTitle());
    }

    @Test
    public void checkCanGetTicketById() {
        Ticket ticket = new Ticket();
        ticket.setTitle("TestTicket");
        ticket.setEmail("testEamil@gmail.com");
        ticket.setDescription("Ticket for test");
        ticket.setPriorityLevel(1);
        ticket.setStatusId(0);

        ticketRepository.save(ticket);

        Optional<Ticket> createdTicket = ticketRepository.findById(ticket.getId());

        assertThat(createdTicket.get()).isNotNull();
        assertThat(createdTicket.get().getTitle()).isEqualTo(ticket.getTitle());
    }

    @Test
    public void checkCanGetAllTicketsInDB() {
        Ticket ticket = new Ticket();
        ticket.setTitle("TestTicket");
        ticket.setEmail("testEamil@gmail.com");
        ticket.setDescription("Ticket for test");
        ticket.setPriorityLevel(1);
        ticket.setStatusId(0);

        Ticket ticket2 = new Ticket();
        ticket2.setTitle("TestTicket");
        ticket2.setEmail("testEamil@gmail.com");
        ticket2.setDescription("Ticket for test");
        ticket2.setPriorityLevel(2);
        ticket2.setStatusId(1);

        ticketRepository.save(ticket);
        ticketRepository.save(ticket2);

        List<Ticket> tickets = ticketRepository.findAll();

        assertThat(tickets.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    public void checkRemovedTicketByIdWasRemoved() {
        Ticket ticket = new Ticket();
        ticket.setTitle("TestTicket");
        ticket.setEmail("testEamil@gmail.com");
        ticket.setDescription("Ticket for test");
        ticket.setPriorityLevel(1);
        ticket.setStatusId(0);

        ticketRepository.save(ticket);
        ticketRepository.deleteById(ticket.getId());

        Optional<Ticket> deletedTicket = ticketRepository.findById(ticket.getId());
        assertThat(deletedTicket.isPresent()).isFalse();
    }

    @Test
    public void checkUpdatedTicketSaved() {
        Ticket ticket = new Ticket();
        ticket.setTitle("TestTicket");
        ticket.setEmail("testEamil@gmail.com");
        ticket.setDescription("Ticket for test");
        ticket.setPriorityLevel(1);
        ticket.setStatusId(0);

        ticketRepository.save(ticket);

        ticket.setTitle("UpdatedTicket");

        ticketRepository.save(ticket);

        Optional<Ticket> updatedTicket = ticketRepository.findById(ticket.getId());

        assertThat(updatedTicket.isPresent()).isTrue();
        assertThat(updatedTicket.get().getTitle()).isEqualTo(ticket.getTitle());
    }
}
