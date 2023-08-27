package com.ebooking.service.impl;

import com.ebooking.entities.Movie;
import com.ebooking.entities.Screen;
import com.ebooking.entities.Theatre;
import com.ebooking.exception.ResourceNotFoundException;
import com.ebooking.payload.MovieDto;
import com.ebooking.repositories.MovieRepository;
import com.ebooking.repositories.ScreenRepository;
import com.ebooking.repositories.TheatreRepository;
import com.ebooking.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private ModelMapper mapper;
    private TheatreRepository theatreRepository;
    private ScreenRepository screenRepository;
    private MovieRepository movieRepository;

    public MovieServiceImpl(ModelMapper mapper, TheatreRepository theatreRepository, ScreenRepository screenRepository, MovieRepository movieRepository) {
        this.mapper = mapper;
        this.theatreRepository = theatreRepository;
        this.screenRepository = screenRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = mapToEntity(movieDto);
        Movie savedMovie = movieRepository.save(movie);
        return mapToDto(savedMovie);
    }

    @Override
    public MovieDto getMovieById(Long theatreId, Long screenId, Long movieId) throws Exception {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException("Movie", "Id", Long.toString(movieId))
        );
        if(!theatre.getScreens().contains(screen) || !screen.getMovies().contains(movie)){
            throw new Exception("MovieServiceImpl -> Data mismatch with passed parameters and db records");
        }
        return mapToDto(movie);
    }

    @Override
    public MovieDto getMovieByName(String name) throws Exception {
        Movie movie = movieRepository.findByName(name);
        if(movie == null){
            throw new Exception("MovieServiceImpl -> Data mismatch with passed parameters and db records");
        }
        return mapToDto(movie);
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(mv -> mapToDto(mv)).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMoviesByTheatre(Long theatreId) {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        return theatre.getMovies().stream().map(mv->mapToDto(mv)).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMoviesByScreens(Long theatreId, Long screenId) throws Exception {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        if(!theatre.getScreens().contains(screen)){
            throw new Exception("MovieServiceImpl -> Data mismatch with passed parameters and db records");
        }
        return screen.getMovies().stream().map(mv->mapToDto(mv)).collect(Collectors.toList());
    }

    @Override
    public MovieDto updateMovie(Long theatreId, Long screenId, Long movieId, MovieDto movieDto) throws Exception {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException("Movie", "Id", Long.toString(movieId))
        );
        if(!theatre.getScreens().contains(screen) || !screen.getMovies().contains(movie) || movieId != movieDto.getId()){
            throw new Exception("MovieServiceImpl -> Data mismatch with passed parameters and db records");
        }
        Movie toBeUpdated = mapToEntity(movieDto);
        Movie updatedMovie = movieRepository.save(toBeUpdated);
        return mapToDto(updatedMovie);
    }

    @Override
    public void deleteMovie(Long theatreId, Long screenId, Long movieId) throws Exception {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException("Movie", "Id", Long.toString(movieId))
        );
        if(!theatre.getScreens().contains(screen) || !screen.getMovies().contains(movie)){
            throw new Exception("MovieServiceImpl -> Data mismatch with passed parameters and db records");
        }
        movieRepository.delete(movie);
    }
    private Movie mapToEntity(MovieDto movieDto){
        return mapper.map(movieDto,Movie.class);
    }
    private MovieDto mapToDto(Movie movie){
        return mapper.map(movie,MovieDto.class);
    }
}
