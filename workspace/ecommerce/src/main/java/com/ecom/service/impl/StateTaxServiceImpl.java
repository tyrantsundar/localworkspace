package com.ecom.service.impl;

import com.ecom.entities.StateTax;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.StateTaxDto;
import com.ecom.repository.StateTaxRepository;
import com.ecom.service.StateTaxService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class StateTaxServiceImpl implements StateTaxService {
    private StateTaxRepository stateTaxRepository;
    public StateTaxServiceImpl(StateTaxRepository stateTaxRepository) {
        this.stateTaxRepository = stateTaxRepository;
    }
    private StateTax mapToEntity(StateTaxDto stateTaxDto){
        StateTax stateTax = new StateTax();
        stateTax.setStateName(stateTaxDto.getStateName());
        stateTax.setSalesTaxRate(stateTaxDto.getSalesTaxRate());
        return stateTax;
    }
    private StateTax updateEntity(StateTaxDto stateTaxDto, StateTax stateTax){
        stateTax.setStateName(stateTaxDto.getStateName());
        stateTax.setSalesTaxRate(stateTaxDto.getSalesTaxRate());
        return stateTax;
    }
    private StateTaxDto mapToDto(StateTax stateTax){
        StateTaxDto stateTaxDto = new StateTaxDto();
        stateTaxDto.setStateName(stateTax.getStateName());
        stateTaxDto.setSalesTaxRate(stateTax.getSalesTaxRate());
        return stateTaxDto;
    }
    @Override
    public StateTaxDto createStateTax(StateTaxDto stateTaxDto) {
        StateTax stateTax = mapToEntity(stateTaxDto);
        StateTax savedStateTax = stateTaxRepository.save(stateTax);
        return mapToDto(savedStateTax);
    }

    @Override
    public StateTaxDto getStateTaxByName(String stateName) {
        StateTax stateTax = stateTaxRepository.findById(stateName).orElseThrow(()
                -> new ResourceNotFoundException("StateTax", "stateName", stateName));
        return mapToDto(stateTax);
    }

    @Override
    public List<StateTaxDto> getAllStatesTax() {
        return stateTaxRepository.findAll().stream().map(st-> mapToDto(st)).collect(Collectors.toList());
    }

    @Override
    public StateTaxDto updateStateTax(String stateName, StateTaxDto stateTaxDto) {
        StateTax stateTax = stateTaxRepository.findById(stateName).orElseThrow(()
                -> new ResourceNotFoundException("StateTax", "stateName", stateName));
        stateTax = updateEntity(stateTaxDto,stateTax);
        StateTax updatedStateTax = stateTaxRepository.save(stateTax);
        return mapToDto(updatedStateTax);
    }

    @Override
    public void deleteStateTax(String stateName) {
        stateTaxRepository.findById(stateName).orElseThrow(()
                -> new ResourceNotFoundException("StateTax", "stateName", stateName));
        stateTaxRepository.deleteById(stateName);
    }
}
