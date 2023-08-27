package com.ebooking.service.impl;

import com.ebooking.entities.Screen;
import com.ebooking.exception.ResourceNotFoundException;
import com.ebooking.payload.ScreenDto;
import com.ebooking.repositories.ScreenRepository;
import com.ebooking.repositories.TheatreRepository;
import com.ebooking.service.ScreenService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreenServiceImpl implements ScreenService {
    private ModelMapper mapper;
    private ScreenRepository screenRepository;
    private TheatreRepository theatreRepository;
    @Override
    public ScreenDto createScreen(ScreenDto screenDto) {
        Screen screen = mapToEntity(screenDto);
        Screen savedScreen = screenRepository.save(screen);
        return mapToDto(savedScreen);
    }

    @Override
    public ScreenDto getScreenById(Long id) {
        Screen screen = screenRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(id))
        );
        return mapToDto(screen);
    }

    @Override
    public ScreenDto getScreenByScreenNo(Long theatreId, int screenNo) {
        theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        List<Screen> screens = screenRepository.findByTheatreId(theatreId);
        Screen screen = screens.stream().filter(sc -> sc.getScreenNo() == screenNo).collect(Collectors.toList()).get(0);
        return mapToDto(screen);
    }

    @Override
    public List<ScreenDto> getAllScreens(Long theatreId) {
        theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        List<Screen> screens = screenRepository.findByTheatreId(theatreId);
        return screens.stream().map(sc -> mapToDto(sc)).collect(Collectors.toList());
    }

    @Override
    public ScreenDto updateScreen(Long theatreId, Long screenId, ScreenDto screenDto) throws Exception {
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        if(screen.getTheatre().getId() != theatreId){
            throw new Exception("Screen's theatre Id "+screen.getTheatre().getId()+" not equals to sent theatre Id "+theatreId);
        }
        screen = mapToEntity(screenDto);
        Screen savedScreen = screenRepository.save(screen);
        return mapToDto(savedScreen);
    }

    @Override
    public void deleteScreen(Long theatreId, Long screenId) {
        screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        screenRepository.deleteById(screenId);
    }
    private ScreenDto mapToDto(Screen screen){
        return mapper.map(screen,ScreenDto.class);
    }
    private Screen mapToEntity(ScreenDto screenDto){
        return mapper.map(screenDto,Screen.class);
    }
}
