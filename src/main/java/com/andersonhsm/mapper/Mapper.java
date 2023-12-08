package com.andersonhsm.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;

public class Mapper {

    private static ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        return origin.stream().map(element -> mapper.map(element, destination)).toList();
    }
}
