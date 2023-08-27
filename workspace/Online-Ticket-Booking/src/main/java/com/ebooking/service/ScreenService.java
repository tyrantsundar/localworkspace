package com.ebooking.service;

import com.ebooking.payload.ScreenDto;

import java.util.List;

public interface ScreenService {
    public ScreenDto createScreen(ScreenDto screenDto);
    public ScreenDto getScreenById(Long id);
    public ScreenDto getScreenByScreenNo(Long theatreId, int screenNo);
    public List<ScreenDto> getAllScreens(Long theatreId);
    public ScreenDto updateScreen(Long theatreId, Long screenId, ScreenDto screenDto) throws Exception;
    public void deleteScreen(Long theatreId, Long screenId);
}
