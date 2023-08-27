package com.ebooking.service;

import com.ebooking.payload.SeatDto;

import java.util.List;

public interface SeatService {
    public SeatDto createSeat(SeatDto seatDto);
    public List<SeatDto> getAllSeats(Long theatreId, Long screenId) throws Exception;
    public SeatDto getSeatByRowAndCol(Long theatreId, Long screenId, int rowNo, int colNo) throws Exception;
    public SeatDto updateSeat(Long theatreId, Long screenId, int rowNo, int colNo, SeatDto seatDto) throws Exception;
    public void deleteSeat(Long theatreId, Long screenId, int rowNo, int colNo) throws Exception;
}
