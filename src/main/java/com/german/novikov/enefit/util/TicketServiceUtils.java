package com.german.novikov.enefit.util;

import com.german.novikov.enefit.model.Ticket;
import com.german.novikov.enefit.model.api.TicketCreationRequest;
import com.german.novikov.enefit.model.api.TicketUpdatingRequest;


public class TicketServiceUtils {

    public static Ticket updatingRequestMapToTicket(TicketUpdatingRequest request) {
        Ticket ticket = new Ticket();
        ticket.setId(request.getId());
        ticket.setTitle(request.getTitle());
        ticket.setEmail(request.getEmail());
        ticket.setDescription(request.getDescription());
        ticket.setPriorityLevel(request.getPriorityLevel());
        ticket.setStatusId(request.getStatusId());
        return ticket;
    }

    public static Ticket creationRequestMapToTicket(TicketCreationRequest request) {
        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setEmail(request.getEmail());
        ticket.setDescription(request.getDescription());
        ticket.setPriorityLevel(request.getPriorityLevel());
        return ticket;
    }
}
