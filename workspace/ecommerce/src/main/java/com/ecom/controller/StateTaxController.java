package com.ecom.controller;

import com.ecom.payload.StateTaxDto;
import com.ecom.service.StateTaxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/stateTax")
public class StateTaxController {
    private StateTaxService stateTaxService;

    public StateTaxController(StateTaxService stateTaxService) {
        this.stateTaxService = stateTaxService;
    }
    @PostMapping
    public ResponseEntity<StateTaxDto> createStateTax(@RequestBody StateTaxDto stateTaxDto){
        return new ResponseEntity<>(stateTaxService.createStateTax(stateTaxDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<StateTaxDto>> getAllStatesTax(){
        return new ResponseEntity<>(stateTaxService.getAllStatesTax(),HttpStatus.OK);
    }
    @GetMapping("/{stateName}")
    public ResponseEntity<StateTaxDto> getStateTaxByName(@PathVariable(value = "stateName")String stateName){
        return new ResponseEntity<>(stateTaxService.getStateTaxByName(stateName),HttpStatus.OK);
    }
    @PutMapping("/{stateName}")
    public ResponseEntity<StateTaxDto> updateStateTax(@PathVariable(value = "stateName")String stateName
                            , @RequestBody StateTaxDto stateTaxDto){
        return new ResponseEntity<>(stateTaxService.updateStateTax(stateName,stateTaxDto),HttpStatus.OK);
    }
    @DeleteMapping("/{stateName}")
    public ResponseEntity<String> deleteShippingType(@PathVariable(value = "stateName")String stateName){
        stateTaxService.deleteStateTax(stateName);
        return new ResponseEntity<>("StateTax Deleted Successfully",HttpStatus.OK);
    }
}

