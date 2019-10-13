package com.german.novikov.enefit.model;

import com.german.novikov.enefit.model.enums.Priority;
import com.german.novikov.enefit.model.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "TICKETS")
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "email")
    private String email;
    @Column(name = "description")
    private String description;
    @Column(name = "priorityLevel")
    private int priorityLevel;
    @Column(name = "statusId")
    private int statusId;
    private Priority priority;
    private Status status;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdDate")
    private Date createdDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modifiedDate")
    private Date modifiedDate;

    public Ticket(String title, String email, String description, int priorityLevel, int statusId) {
        this.id = id;
        this.title = title;
        this.email = email;
        this.description = description;
        this.priorityLevel = priorityLevel;
        this.priority = Priority.getByLevel(priorityLevel);
        this.statusId = statusId;
        this.status = Status.getById(statusId);
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
        this.priority = Priority.getByLevel(priorityLevel);
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
        this.status = Status.getById(statusId);
    }
}
