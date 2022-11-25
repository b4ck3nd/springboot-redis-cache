package com.example.springbootredis.service;

import com.example.springbootredis.dto.request.MovieCreateRequestDto;
import com.example.springbootredis.dto.request.MovieUpdateRequestDto;
import com.example.springbootredis.dto.response.MovieResponseDto;
import com.example.springbootredis.model.Movie;

import java.util.List;

public interface MovieService {

    MovieResponseDto add(MovieCreateRequestDto movie);
    List<MovieResponseDto> findAll();
    MovieResponseDto findById(long id);
    MovieResponseDto updateMovie(MovieUpdateRequestDto dto);
    void deleteById(long id);
}
