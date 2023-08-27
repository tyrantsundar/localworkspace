package com.ebooking.controller;

import com.ebooking.payload.ShowDto;
import com.ebooking.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/show")
public class ShowController {
    private ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }


//    public ShowDto updateShow(Long theatreId, Long screenId, Long showId, ShowDto showDto) throws Exception;
//    public void deleteShow(Long theatreId, Long screenId, Long showId) throws Exception;

    @PostMapping
    public ResponseEntity<ShowDto> createShow(@RequestBody ShowDto showDto){
        return new ResponseEntity<>(showService.createShow(showDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowDto> getShowById(@PathVariable(value = "id")long id){
        return new ResponseEntity<>(showService.getShowById(id), HttpStatus.OK);
    }

    @GetMapping("/{theatreId}/{movieName}")
    public ResponseEntity<List<ShowDto>> getShowsByMovie(
            @PathVariable(value = "theatreId")long theatreId,
            @PathVariable(value = "movieName")String movieName
            ){
        return new ResponseEntity<>(showService.getShowsByMovie(theatreId,movieName), HttpStatus.OK);
    }

    @GetMapping("/{time}")
    public ResponseEntity<List<ShowDto>> getShowByTime(
            @PathVariable(value = "time")String time
    ){
        return new ResponseEntity<>(showService.getShowByTime(time), HttpStatus.OK);
    }

    @GetMapping("/{theatreId}/{screenId}")
    public ResponseEntity<List<ShowDto>> getShowsByScreen(
            @PathVariable(value = "theatreId")long theatreId,
            @PathVariable(value = "screenId")long screenId
    ){
        return new ResponseEntity<>(showService.getShowsByScreen(theatreId, screenId), HttpStatus.OK);
    }

    @PostMapping("/{theatreId}/{screenId}/{showId}")
    public ResponseEntity<Object> getShowsByScreen(
            @PathVariable(value = "theatreId")long theatreId,
            @PathVariable(value = "screenId")long screenId,
            @PathVariable(value = "showId")long showId,
            @RequestBody ShowDto showDto
    ){
        try {
            return new ResponseEntity<>(showService.updateShow(theatreId, screenId, showId, showDto), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{theatreId}/{screenId}/{showId}")
    public ResponseEntity<Object> deleteShow(
            @PathVariable(value = "theatreId")long theatreId,
            @PathVariable(value = "screenId")long screenId,
            @PathVariable(value = "showId")long showId
    ){
        try {
            showService.deleteShow(theatreId, screenId, showId);
            return  new ResponseEntity<>("Deleted SuccessFully !", HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
