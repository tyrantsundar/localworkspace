package com.ebooking.service;

import com.ebooking.payload.ShowDto;

import java.util.List;

public interface ShowService {
    public ShowDto createShow(ShowDto showDto);
    public ShowDto getShowById(Long id);
    public List<ShowDto> getShowsByMovie(Long theatreId, String movieName);
    public List<ShowDto> getShowByTime(String time);
    public List<ShowDto> getShowsByScreen(Long theatreId, Long screenId);
    public ShowDto updateShow(Long theatreId, Long screenId, Long showId, ShowDto showDto) throws Exception;
    public void deleteShow(Long theatreId, Long screenId, Long showId) throws Exception;
}
