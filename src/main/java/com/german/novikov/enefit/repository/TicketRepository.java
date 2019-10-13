package com.german.novikov.enefit.repository;

import com.german.novikov.enefit.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    void deleteById(Long id);
    Optional<Ticket> findById(Long id);


}
