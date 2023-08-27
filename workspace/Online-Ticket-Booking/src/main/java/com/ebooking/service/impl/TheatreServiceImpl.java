package com.ebooking.service.impl;

import com.ebooking.entities.Movie;
import com.ebooking.entities.Theatre;
import com.ebooking.exception.ResourceNotFoundException;
import com.ebooking.payload.TheatreDto;
import com.ebooking.repositories.TheatreRepository;
import com.ebooking.service.TheatreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheatreServiceImpl implements TheatreService {
    private TheatreRepository theatreRepository;
    private ModelMapper mapper;

    public TheatreServiceImpl(TheatreRepository theatreRepository, ModelMapper mapper) {
        this.theatreRepository = theatreRepository;
        this.mapper = mapper;
    }

    @Override
    public TheatreDto createTheatre(TheatreDto theatreDto) {
        Theatre theatreObj = mapToEntity(theatreDto);
        Theatre savedTheatre = theatreRepository.save(theatreObj);
        return mapToDto(savedTheatre);
    }

    @Override
    public TheatreDto getTheatreById(Long id) {
        Theatre theatre = theatreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(id))
        );
        return mapToDto(theatre);
    }

    @Override
    public TheatreDto getTheatreByName(String name) {
        Theatre theatre = theatreRepository.findByName(name);
        if (theatre != null){
            return mapToDto(theatre);
        }
        throw new ResourceNotFoundException("Theatre", "Name", name);
    }

    @Override
    public List<TheatreDto> getAllTheatres() {
        return theatreRepository.findAll().stream().map(th -> mapToDto(th)).collect(Collectors.toList());
    }

    @Override
    public List<TheatreDto> getTheatresByMovie(String movieName) {
        List<Theatre> allTheatres = theatreRepository.findAll();
        List<TheatreDto> movieTheatre = new ArrayList<>();
        for(Theatre theatre : allTheatres){
            if( theatre.getMovies().stream().filter(mv -> mv.getName().equalsIgnoreCase(movieName)).count() > 0 ){
                movieTheatre.add(mapToDto(theatre));
            }
        }
        return movieTheatre;
    }

    @Override
    public void deleteTheatreById(Long id) {
        theatreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(id))
        );
        theatreRepository.deleteById(id);
    }

    @Override
    public TheatreDto updateTheatreById(Long id, TheatreDto theatreDto) {
        theatreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(id))
        );
        Theatre theatre = mapToEntity(theatreDto);
        Theatre updatedTheatre = theatreRepository.save(theatre);
        return mapToDto(updatedTheatre);
    }
    private Theatre mapToEntity(TheatreDto theatreDto){
        return mapper.map(theatreDto, Theatre.class);
    }
    private TheatreDto mapToDto(Theatre theatre){
        return mapper.map(theatre, TheatreDto.class);
    }
}
