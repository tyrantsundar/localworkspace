package com.ebooking.controller;

import com.ebooking.payload.SeatDto;
import com.ebooking.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping
    public ResponseEntity<SeatDto> createSeat(@RequestBody SeatDto seatDto){
        return  new ResponseEntity<>(seatService.createSeat(seatDto), HttpStatus.CREATED);
    }

    @GetMapping("/{theatreId}/{screenId}")
    public ResponseEntity<Object> getAllSeats(
            @PathVariable(value = "theatreId") long theatreId,
            @PathVariable(value = "screenId") long screenId){
        try {
            return  new ResponseEntity<>(seatService.getAllSeats(theatreId, screenId), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{theatreId}/{screenId}/{rowNo}/{colNo}")
    public ResponseEntity<Object> getSeatByRowAndCol(
            @PathVariable(value = "theatreId") long theatreId,
            @PathVariable(value = "screenId") long screenId,
            @PathVariable(value = "rowNo") int rowNo,
            @PathVariable(value = "colNo") int colNo
            ){
        try {
            return  new ResponseEntity<>(seatService.getSeatByRowAndCol(theatreId, screenId,rowNo,colNo), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{theatreId}/{screenId}/{rowNo}/{colNo}")
    public ResponseEntity<Object> updateSeat(
            @PathVariable(value = "theatreId") long theatreId,
            @PathVariable(value = "screenId") long screenId,
            @PathVariable(value = "rowNo") int rowNo,
            @PathVariable(value = "colNo") int colNo,
            @RequestBody SeatDto seatDto
    ){
        try {
            return  new ResponseEntity<>(seatService.updateSeat(theatreId, screenId, rowNo, colNo, seatDto), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{theatreId}/{screenId}/{rowNo}/{colNo}")
    public ResponseEntity<String> deleteSeat(
            @PathVariable(value = "theatreId") long theatreId,
            @PathVariable(value = "screenId") long screenId,
            @PathVariable(value = "rowNo") int rowNo,
            @PathVariable(value = "colNo") int colNo
    ){
        try {
            seatService.deleteSeat(theatreId, screenId,rowNo,colNo);
            return  new ResponseEntity<>("Deleted SuccessFully !", HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
