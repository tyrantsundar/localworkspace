package com.ebooking.repositories;


import com.ebooking.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show,Long> {
    public List<Show> findByShowTime(String time);
}
