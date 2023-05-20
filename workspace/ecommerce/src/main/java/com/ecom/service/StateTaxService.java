package com.ecom.service;

import com.ecom.payload.StateTaxDto;

import java.util.List;

public interface StateTaxService {
    StateTaxDto createStateTax(StateTaxDto stateTaxDto);
    StateTaxDto getStateTaxByName(String stateName);
    List<StateTaxDto> getAllStatesTax();
    StateTaxDto updateStateTax(String stateName, StateTaxDto stateTaxDto);
    void deleteStateTax(String stateName);
}
