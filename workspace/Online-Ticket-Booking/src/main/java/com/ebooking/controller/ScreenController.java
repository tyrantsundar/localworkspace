package com.ebooking.controller;

import com.ebooking.payload.ScreenDto;
import com.ebooking.service.ScreenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/screen"})
public class ScreenController {
    private ScreenService screenService;

    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    @PostMapping
    public ResponseEntity<ScreenDto> createScreen(@RequestBody ScreenDto screenDto){
        return new ResponseEntity<>(screenService.createScreen(screenDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenDto> getScreenById(@PathVariable(value = "id")long id){
        return new ResponseEntity<>(screenService.getScreenById(id), HttpStatus.OK);
    }
    @GetMapping("/{theatreId}/{screenNo}")
    public ResponseEntity<ScreenDto> getScreenByScreenNo(
            @PathVariable(value = "theatreId")long theatreId,
            @PathVariable(value = "screenNo")int screenNo){
        return new ResponseEntity<>(screenService.getScreenByScreenNo(theatreId, screenNo), HttpStatus.OK);
    }

    @GetMapping("/{theatreId}")
    public ResponseEntity<List<ScreenDto>> getAllScreens( @PathVariable(value = "theatreId")long theatreId){
        return new ResponseEntity<>(screenService.getAllScreens(theatreId),HttpStatus.OK);
    }

    @PostMapping("/{theatreId}/{screenId}")
    public ResponseEntity<Object> updateScreen(
            @PathVariable(value = "theatreId")long theatreId,
            @PathVariable(value = "screenId")long screenId,
            @RequestBody ScreenDto screenDto){
        try {
            return new ResponseEntity<>(screenService.updateScreen(theatreId, screenId, screenDto), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{theatreId}/{screenId}")
    public ResponseEntity<Object> deleteScreen(
            @PathVariable(value = "theatreId")long theatreId,
            @PathVariable(value = "screenId")long screenId
    ){
        screenService.deleteScreen(theatreId, screenId);
        return  new ResponseEntity<>("Deleted SuccessFully !", HttpStatus.OK);
    }

}
