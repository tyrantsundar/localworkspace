package com.ebooking.service;

import com.ebooking.payload.MovieDto;

import java.util.List;

public interface MovieService {
    public MovieDto createMovie(MovieDto movieDto);
    public MovieDto getMovieById(Long theatreId, Long screenId, Long movieId) throws Exception;
    public MovieDto getMovieByName(String name) throws Exception;
    public List<MovieDto> getAllMovies();
    public List<MovieDto> getMoviesByTheatre(Long theatreId);
    public List<MovieDto> getMoviesByScreens(Long theatreId, Long screenId) throws Exception;
    public MovieDto updateMovie(Long theatreId, Long screenId, Long MovieId, MovieDto movieDto) throws Exception;
    public void deleteMovie(Long theatreId, Long screenId, Long movieId) throws Exception;
}
