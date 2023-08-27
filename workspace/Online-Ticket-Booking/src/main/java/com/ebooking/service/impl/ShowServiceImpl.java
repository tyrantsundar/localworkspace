package com.ebooking.service.impl;

import com.ebooking.entities.Movie;
import com.ebooking.entities.Screen;
import com.ebooking.entities.Show;
import com.ebooking.entities.Theatre;
import com.ebooking.exception.ResourceNotFoundException;
import com.ebooking.payload.ShowDto;
import com.ebooking.repositories.MovieRepository;
import com.ebooking.repositories.ScreenRepository;
import com.ebooking.repositories.ShowRepository;
import com.ebooking.repositories.TheatreRepository;
import com.ebooking.service.ShowService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ShowServiceImpl implements ShowService {
    private ModelMapper mapper;
    private TheatreRepository theatreRepository;
    private MovieRepository movieRepository;
    private ShowRepository showRepository;
    private ScreenRepository screenRepository;

    public ShowServiceImpl(ModelMapper mapper, TheatreRepository theatreRepository, MovieRepository movieRepository, ShowRepository showRepository, ScreenRepository screenRepository) {
        this.mapper = mapper;
        this.theatreRepository = theatreRepository;
        this.movieRepository = movieRepository;
        this.showRepository = showRepository;
        this.screenRepository = screenRepository;
    }

    @Override
    public ShowDto createShow(ShowDto showDto) {
        Show show = mapToEntity(showDto);
        Show savedShow = showRepository.save(show);
        return mapToDto(savedShow);
    }

    @Override
    public ShowDto getShowById(Long id) {
        Show show = showRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Show", "Id", Long.toString(id))
        );
        return mapToDto(show);
    }

    @Override
    public List<ShowDto> getShowsByMovie(Long theatreId, String movieName) {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        List<ShowDto>  showList = new ArrayList<>();
        for(Screen screen: theatre.getScreens()){
            for(Show show : screen.getShows()){
                if(show.getMovie().getName().equalsIgnoreCase(movieName)){
                    showList.add(mapToDto(show));
                }
            }
        }
        return showList;
    }

    @Override
    public List<ShowDto> getShowByTime(String time) {
        List<Show> showList = showRepository.findByShowTime(time);
        if(showList == null || showList.size() ==0 ){
            throw new ResourceNotFoundException("Show","time",time);
        }
        return showList.stream().map(sh -> mapToDto(sh)).collect(Collectors.toList());
    }

    @Override
    public List<ShowDto> getShowsByScreen(Long theatreId, Long screenId) {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        if(theatre.getScreens().contains(screen)){
            return screen.getShows().stream().map(sh -> mapToDto(sh)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public ShowDto updateShow(Long theatreId, Long screenId, Long showId, ShowDto showDto) throws Exception {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        Show show = showRepository.findById(showId).orElseThrow(
                () -> new ResourceNotFoundException("Show", "Id", Long.toString(showId))
        );
        if(!theatre.getScreens().contains(screen) || !screen.getShows().contains(show) || showDto.getId() != showId){
            throw new Exception("ShowServiceImpl -> Data mismatch with passed parameters and db records");
        }
        Show toBeUpdatedShow = mapToEntity(showDto);
        Show updatedShow = showRepository.save(toBeUpdatedShow);
        return mapToDto(updatedShow);
    }

    @Override
    public void deleteShow(Long theatreId, Long screenId, Long showId) throws Exception {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        Show show = showRepository.findById(showId).orElseThrow(
                () -> new ResourceNotFoundException("Show", "Id", Long.toString(showId))
        );
        if(!theatre.getScreens().contains(screen) || !screen.getShows().contains(show)){
            throw new Exception("ShowServiceImpl -> Data mismatch with passed parameters and db records");
        }
        showRepository.delete(show);
    }
    private ShowDto mapToDto(Show show){
        return mapper.map(show, ShowDto.class);
    }
    private Show mapToEntity(ShowDto showDto){
        return mapper.map(showDto, Show.class);
    }
}
