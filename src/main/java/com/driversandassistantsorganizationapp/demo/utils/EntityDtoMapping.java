package com.driversandassistantsorganizationapp.demo.utils;

import org.modelmapper.ModelMapper;

public class EntityDtoMapping {
    private static ModelMapper modelMapper = new ModelMapper();

    public static <T> Object convertEntityToDto(Object entity, Class<T> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <T> Object convertDtoToEntity(Object dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
