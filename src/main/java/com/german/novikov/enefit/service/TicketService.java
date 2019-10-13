package com.german.novikov.enefit.service;

import com.german.novikov.enefit.exception.ApplicationException;
import com.german.novikov.enefit.model.Ticket;
import com.german.novikov.enefit.model.api.TicketCreationRequest;
import com.german.novikov.enefit.model.api.TicketUpdatingRequest;
import com.german.novikov.enefit.model.enums.Priority;
import com.german.novikov.enefit.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.german.novikov.enefit.util.TicketServiceUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private static final int STATUS_ID_OF_NEW_TICKET = 0;

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    public Ticket create(TicketCreationRequest request) {
        Ticket ticket = creationRequestMapToTicket(request);
        ticket.setStatusId(STATUS_ID_OF_NEW_TICKET);

        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(!ticket.isPresent()){
            String message = String.format("Ticket with id %s not exist", id);
            log.error(message);
            throw new ApplicationException(message);
        }
        return ticket.get();
    }

    public Ticket update(TicketUpdatingRequest request) {
        Ticket ticket = updatingRequestMapToTicket(request);
        return ticketRepository.save(ticket);
    }

    public void delete(long id) {
        ticketRepository.deleteById(id);
    }

    public Map<Integer, String> getPriorityList() {
        return Arrays.stream(Priority.values())
                .collect(Collectors.toMap(Priority::getLevel, Priority::getName));
    }

    public List<Ticket> sortTicketsByPriority(String orderBy) {
        List<Ticket> tickets = getAllTickets();
        return tickets.stream().sorted(new Comparator<Ticket>() {
            @Override
            public int compare(Ticket ticket, Ticket ticket1) {
                if(ticket.getPriorityLevel() < ticket1.getPriorityLevel()){
                    return "DESC".equals(orderBy) ? 1 : -1;
                } else if(ticket.getPriorityLevel() > ticket1.getPriorityLevel()){
                    return "DESC".equals(orderBy) ? -1 : 1;
                }else {
                    return 0;
                }
            }
        }).collect(Collectors.toList());
    }

    public List<Ticket> sortTicketsByCreatedDate(String orderBy) {
        List<Ticket> tickets = getAllTickets();
        return tickets.stream().sorted(new Comparator<Ticket>() {
            @Override
            public int compare(Ticket ticket, Ticket ticket1) {
                if("DESC".equals(orderBy)) {
                    return -ticket.getCreatedDate().compareTo(ticket1.getCreatedDate());
                }else{
                    return ticket.getCreatedDate().compareTo(ticket1.getCreatedDate());
                }

            }
        }).collect(Collectors.toList());
    }

    public List<Ticket> sortTicketsByStatus(String orderBy) {
        List<Ticket> tickets = getAllTickets();
        return tickets.stream().sorted(new Comparator<Ticket>() {
            @Override
            public int compare(Ticket ticket, Ticket ticket1) {
                if(ticket.getStatusId() < ticket1.getStatusId()){
                    return "DESC".equals(orderBy) ? 1 : -1;
                } else if(ticket.getStatusId() > ticket1.getStatusId()){
                    return "DESC".equals(orderBy) ? -1 : 1;
                }else {
                    return 0;
                }
            }
        }).collect(Collectors.toList());
    }
}
