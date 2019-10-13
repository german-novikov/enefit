package com.german.novikov.enefit.controller.api.rest;

import com.german.novikov.enefit.model.Ticket;
import com.german.novikov.enefit.model.api.TicketCreationRequest;
import com.german.novikov.enefit.model.api.TicketUpdatingRequest;
import com.german.novikov.enefit.service.TicketService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @ApiOperation(value = "Create new ticket")
    @RequestMapping(method = PUT)
    @ResponseBody
    public Ticket create(@RequestBody TicketCreationRequest request) {
        return ticketService.create(request);
    }

    @ApiOperation(value = "Getting ticket by id")
    @RequestMapping(method = GET)
    @ResponseBody
    public Ticket getTicketById(@RequestParam long id){
        return ticketService.getTicketById(id);
    }

    @ApiOperation(value = "Update ticket")
    @RequestMapping(value="/update", method = POST)
    @ResponseBody
    public Ticket updateTicket(@RequestBody TicketUpdatingRequest request) {
        return ticketService.update(request);
    }

    @ApiOperation(value = "Delete ticket")
    @RequestMapping(value="/delete", method = GET)
    @ResponseBody
    public void deleteTicket(@RequestParam long id) {
        ticketService.delete(id);
    }

    @ApiOperation(value = "Get ticket priority")
    @RequestMapping(value="/priority", method = GET)
    @ResponseBody
    public Map<Integer, String> getPriority() {
        return ticketService.getPriorityList();
    }
}
