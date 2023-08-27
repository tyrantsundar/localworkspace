package com.ebooking.controller;

import com.ebooking.payload.MovieDto;
import com.ebooking.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto){
        return new ResponseEntity<>(movieService.createMovie(movieDto), HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> getMovieByName(@PathVariable(value = "name") String name){
        try {
            return  new ResponseEntity<>(movieService.getMovieByName(name), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        return  new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{theatreId}")
    public ResponseEntity<List<MovieDto>> getMoviesByTheatre(@PathVariable(value = "theatreId") long theatreId){
        return  new ResponseEntity<>(movieService.getMoviesByTheatre(theatreId), HttpStatus.OK);
    }

    @GetMapping("/{theatreId}/{screenId}/{movieId}")
    public ResponseEntity<Object> getMovieById(
            @PathVariable(value = "theatreId") long theatreId,
            @PathVariable(value = "screenId") long screenId,
            @PathVariable(value = "movieId") long movieId
    ){
        try{
              return  new ResponseEntity<>(movieService.getMovieById(theatreId,screenId,movieId), HttpStatus.OK);
        }catch (Exception ex){
              return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{theatreId}/{screenId}")
    public ResponseEntity<Object> getMoviesByScreens(
            @PathVariable(value = "theatreId") long theatreId,
            @PathVariable(value = "screenId") long screenId
    ){
        try{
            return  new ResponseEntity<>(movieService.getMoviesByScreens(theatreId,screenId), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{theatreId}/{screenId}/{movieId}")
    public ResponseEntity<Object> updateMovie(
            @PathVariable(value = "theatreId") long theatreId,
            @PathVariable(value = "screenId") long screenId,
            @PathVariable(value = "movieId") long movieId,
            @RequestBody MovieDto movieDto
    ){
        try{
            return  new ResponseEntity<>(movieService.updateMovie(theatreId,screenId,movieId, movieDto), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{theatreId}/{screenId}/{movieId}")
    public ResponseEntity<Object> deleteMovieById(
            @PathVariable(value = "theatreId") long theatreId,
            @PathVariable(value = "screenId") long screenId,
            @PathVariable(value = "movieId") long movieId
    ){
        try{
            movieService.deleteMovie(theatreId,screenId,movieId);
            return  new ResponseEntity<>("Deleted Successfully !", HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
