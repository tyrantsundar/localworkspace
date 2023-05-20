package com.ecom.service.impl;

import com.ecom.entities.ShippingType;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.ShippingTypeDto;
import com.ecom.repository.ShippingTypeRepository;
import com.ecom.service.ShippingTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ShippingTypeServiceImpl implements ShippingTypeService {
    private ShippingTypeRepository shippingTypeRepository;

    public ShippingTypeServiceImpl(ShippingTypeRepository shippingTypeRepository) {
        this.shippingTypeRepository = shippingTypeRepository;
    }

    private ShippingTypeDto mapToDto(ShippingType shippingType){
        ShippingTypeDto shippingTypeDto = new ShippingTypeDto();
        shippingTypeDto.setType(shippingType.getType());
        shippingTypeDto.setPrice(shippingType.getPrice());
        shippingTypeDto.setApproxDeliveryDays(shippingType.getApproxDeliveryDays());
        return shippingTypeDto;
    }
    private ShippingType mapToEntity(ShippingTypeDto shippingTypeDto){
        ShippingType shippingType = new ShippingType();
        shippingType.setType(shippingTypeDto.getType());
        shippingType.setPrice(shippingTypeDto.getPrice());
        shippingType.setApproxDeliveryDays(shippingTypeDto.getApproxDeliveryDays());
        return shippingType;
    }
    private ShippingType updateEntity(ShippingTypeDto shippingTypeDto, ShippingType shippingType){
        shippingType.setType(shippingTypeDto.getType());
        shippingType.setPrice(shippingTypeDto.getPrice());
        shippingType.setApproxDeliveryDays(shippingTypeDto.getApproxDeliveryDays());
        return shippingType;
    }
    @Override
    public ShippingTypeDto createShippingType(ShippingTypeDto shippingTypeDto) {
        ShippingType shippingType = mapToEntity(shippingTypeDto);
        ShippingType savedShippingType = shippingTypeRepository.save(shippingType);
        return mapToDto(savedShippingType);
    }

    @Override
    public ShippingTypeDto getShippingTypeById(String type) {
        ShippingType shippingType = shippingTypeRepository.findById(type).orElseThrow(()
                -> new ResourceNotFoundException("ShippingType", "type", type));
        return mapToDto(shippingType);
    }

    @Override
    public List<ShippingTypeDto> getAllShippingType() {
        return shippingTypeRepository.findAll().stream().map(str->mapToDto(str)).collect(Collectors.toList());
    }

    @Override
    public ShippingTypeDto updateShippingType(String type, ShippingTypeDto shippingTypeDto) {
        ShippingType shippingType = shippingTypeRepository.findById(type).orElseThrow(()
                -> new ResourceNotFoundException("ShippingType", "type", type));
        shippingType = updateEntity(shippingTypeDto,shippingType);
        ShippingType updatedShippingType = shippingTypeRepository.save(shippingType);
        return mapToDto(updatedShippingType);
    }

    @Override
    public void deleteShippingType(String type) {
        shippingTypeRepository.findById(type).orElseThrow(()
                -> new ResourceNotFoundException("ShippingType", "type", type));
        shippingTypeRepository.deleteById(type);
    }
}
