package com.german.novikov.enefit.model.api;

import lombok.Data;

@Data
public class TicketUpdatingRequest {
    private long id;
    private String title;
    private String email;
    private String description;
    private int priorityLevel;
    private int statusId;
}
