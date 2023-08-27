package com.ebooking.controller;

import com.ebooking.payload.TheatreDto;
import com.ebooking.service.TheatreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatre")
public class TheatreController {
    private TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }
    @PostMapping
    public ResponseEntity<TheatreDto> createTheatre(@RequestBody TheatreDto theatreDto){
       return new ResponseEntity<>(theatreService.createTheatre(theatreDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TheatreDto>> getAllTheatres(){
        return new ResponseEntity<>(theatreService.getAllTheatres(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheatreDto> getTheatreById(@PathVariable(value="id") long id){
        return new ResponseEntity<>(theatreService.getTheatreById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TheatreDto> getTheatreByName(@PathVariable(value="name") String name){
        return new ResponseEntity<>(theatreService.getTheatreByName(name), HttpStatus.OK);
    }

    @GetMapping("/{movieName}")
    public ResponseEntity<List<TheatreDto>> getTheatreByMovieName(@PathVariable(value="movieName") String movieName){
        return new ResponseEntity<>(theatreService.getTheatresByMovie(movieName), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheatre(@PathVariable(value="id") long id){
        theatreService.deleteTheatreById(id);
        return new ResponseEntity<>("Deleted sucessfully !",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TheatreDto> updateTheatre(@PathVariable(value = "id") long id, @RequestBody TheatreDto theatreDto){
        return new ResponseEntity<>( theatreService.updateTheatreById(id,theatreDto), HttpStatus.OK);
    }
}
