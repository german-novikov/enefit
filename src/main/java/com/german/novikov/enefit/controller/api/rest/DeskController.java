package com.german.novikov.enefit.controller.api.rest;

import com.german.novikov.enefit.model.Ticket;
import com.german.novikov.enefit.service.TicketService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/desk")
@RequiredArgsConstructor
public class DeskController {

    private final TicketService ticketService;

    @RequestMapping( method = GET)
    @ResponseBody
    public Collection<Ticket> showDashboard() {
        return ticketService.getAllTickets();
    }

    @ApiOperation(value = "Give sorted list by priority", notes = "Request parameter must be 'DESC' or 'ASC'")
    @RequestMapping(value = "/sort_by_priority", method = GET)
    @ResponseBody
    public Collection<Ticket> sortTicketsByPriority(@RequestParam String orderBy) {
        return ticketService.sortTicketsByPriority(orderBy);
    }

    @ApiOperation(value = "Give sorted list by created date", notes = "Request parameter must be 'DESC' or 'ASC'")
    @RequestMapping(value = "/sort_by_created_date", method = GET)
    @ResponseBody
    public Collection<Ticket> sortTicketsByCreatedDate(@RequestParam String orderBy) {
        return ticketService.sortTicketsByCreatedDate(orderBy);
    }

    @ApiOperation(value = "Give sorted list by created date", notes = "Request parameter must be 'DESC' or 'ASC'")
    @RequestMapping(value = "/sort_by_status", method = GET)
    @ResponseBody
    public Collection<Ticket> sortTicketsByStatus(@RequestParam String orderBy) {
        return ticketService.sortTicketsByStatus(orderBy);
    }

}
