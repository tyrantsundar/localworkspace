package com.ebooking.repositories;

import com.ebooking.entities.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen,Long> {
    public List<Screen> findByTheatreId(Long theatreId);
}
