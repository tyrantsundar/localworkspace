package com.ebooking.service;

import com.ebooking.payload.TheatreDto;

import java.util.List;

public interface TheatreService {
    public TheatreDto createTheatre(TheatreDto theatreDto);
    public TheatreDto getTheatreById(Long id);
    public TheatreDto getTheatreByName(String name);
    public List<TheatreDto> getAllTheatres();
    public List<TheatreDto> getTheatresByMovie(String movieName);
    public void deleteTheatreById(Long id);
    public TheatreDto updateTheatreById(Long id, TheatreDto theatreDto);
}
