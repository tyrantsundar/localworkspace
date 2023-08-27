package com.ebooking.repositories;

import com.ebooking.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre,Long> {
    public Theatre findByName(String name);
}
