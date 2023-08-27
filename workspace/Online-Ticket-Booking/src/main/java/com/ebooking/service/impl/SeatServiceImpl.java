package com.ebooking.service.impl;

import com.ebooking.entities.Screen;
import com.ebooking.entities.Seat;
import com.ebooking.entities.Theatre;
import com.ebooking.exception.ResourceNotFoundException;
import com.ebooking.payload.SeatDto;
import com.ebooking.repositories.ScreenRepository;
import com.ebooking.repositories.SeatRepository;
import com.ebooking.repositories.TheatreRepository;
import com.ebooking.service.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class SeatServiceImpl implements SeatService {
    private ModelMapper modelMapper;
    private SeatRepository seatRepository;
    private ScreenRepository screenRepository;
    private TheatreRepository theatreRepository;

    public SeatServiceImpl(ModelMapper modelMapper, SeatRepository seatRepository, ScreenRepository screenRepository, TheatreRepository theatreRepository) {
        this.modelMapper = modelMapper;
        this.seatRepository = seatRepository;
        this.screenRepository = screenRepository;
        this.theatreRepository = theatreRepository;
    }

    @Override
    public SeatDto createSeat(SeatDto seatDto) {
        Seat seat = mapToEntity(seatDto);
        Seat savedSeat = seatRepository.save(seat);
        return mapToDto(savedSeat);
    }

    @Override
    public List<SeatDto> getAllSeats(Long theatreId, Long screenId) throws Exception {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        if(!theatre.getScreens().contains(screen)){
            throw new Exception("SeatServiceImpl -> Data mismatch with passed parameters and db records");
        }
        return screen.getSeats().stream().map(st -> mapToDto(st)).collect(Collectors.toList());
    }

    @Override
    public SeatDto getSeatByRowAndCol(Long theatreId, Long screenId, int rowNo, int colNo) throws Exception {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        Seat seat = seatRepository.findByRowNoAndColNoAndScreenId(rowNo, colNo, screenId);
        if(!theatre.getScreens().contains(screen) || seat == null){
            throw new Exception("SeatServiceImpl -> Data mismatch with passed parameters and db records");
        }
        return mapToDto(seat);
//        List<Seat> filteredSeats = screen.getSeats().stream().filter(st -> st.getRowNo() == rowNo && st.getColNo() == colNo).collect(Collectors.toList());
//        if(filteredSeats == null || filteredSeats.size() == 0){
//
//        }
//        return mapToDto(filteredSeats.get(0));
    }

    @Override
    public SeatDto updateSeat(Long theatreId, Long screenId, int rowNo, int colNo, SeatDto seatDto) throws Exception {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );

        Seat seat = seatRepository.findByRowNoAndColNoAndScreenId(rowNo, colNo, screenId);
        if(!theatre.getScreens().contains(screen) || seat == null){
            throw new Exception("SeatServiceImpl -> Data mismatch with passed parameters and db records");
        }

        Seat updatedSeat = seatRepository.save(mapToEntity(seatDto));
        return mapToDto(updatedSeat);
    }

    @Override
    public void deleteSeat(Long theatreId, Long screenId, int rowNo, int colNo) throws Exception {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(
                () -> new ResourceNotFoundException("Theatre", "Id", Long.toString(theatreId))
        );
        Screen screen = screenRepository.findById(screenId).orElseThrow(
                () -> new ResourceNotFoundException("Screen", "Id", Long.toString(screenId))
        );
        Seat seat = seatRepository.findByRowNoAndColNoAndScreenId(rowNo, colNo, screenId);
        if(!theatre.getScreens().contains(screen) || seat == null){
            throw new Exception("SeatServiceImpl -> Data mismatch with passed parameters and db records");
        }
        seatRepository.delete(seat);
    }
    private SeatDto mapToDto(Seat seat){
        return modelMapper.map(seat,SeatDto.class);
    }
    private Seat mapToEntity(SeatDto seatDto){
        return modelMapper.map(seatDto,Seat.class);
    }
}
