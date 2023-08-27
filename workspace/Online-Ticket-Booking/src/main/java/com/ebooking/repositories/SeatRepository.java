package com.ebooking.repositories;

import com.ebooking.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    public Seat findByRowNoAndColNoAndScreenId(int rowNo,int colNo, long screenId);
}
