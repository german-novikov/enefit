package com.german.novikov.enefit.model.api;

import lombok.Data;

@Data
public class TicketCreationRequest {
    private String title;
    private String email;
    private String description;
    private int priorityLevel;
}
