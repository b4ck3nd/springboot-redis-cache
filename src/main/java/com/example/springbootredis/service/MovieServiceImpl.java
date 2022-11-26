package com.example.springbootredis.service;


import com.example.springbootredis.dto.request.MovieCreateRequestDto;
import com.example.springbootredis.dto.request.MovieUpdateRequestDto;
import com.example.springbootredis.dto.response.MovieResponseDto;
import com.example.springbootredis.model.Movie;
import com.example.springbootredis.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    private final ModelMapper mapper;


    @CachePut(cacheNames = "movie",key = "#id")
    @Override
    public MovieResponseDto add(MovieCreateRequestDto movie) {
        Movie save = movieRepository.save(mapper.map(movie, Movie.class));
        return mapper.map(save,MovieResponseDto.class);
    }

    @Cacheable(cacheNames = "movies")
    @Override
    public List<MovieResponseDto> findAll() {
        List<MovieResponseDto> dtos=new ArrayList<>();

        List<Movie> all = movieRepository.findAll();
        for (Movie movie : all) {
            dtos.add(mapper.map(movie,MovieResponseDto.class));
        }
        return dtos;
    }

    @Cacheable(cacheNames = "movies",key = "#id")
    @Override
    public MovieResponseDto findById(long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(RuntimeException::new);
        return mapper.map(movie,MovieResponseDto.class);
    }


    @CachePut(cacheNames = "movies",key = "#dto.id",condition = "#result != null")
    @Override
    public MovieResponseDto updateMovie(MovieUpdateRequestDto dto) {
        Optional<Movie> by = movieRepository.findById(dto.getId());
        if (by.isPresent()) {
            Movie movie = by.get();
            movie.setName(dto.getName());
            movie.setDirector(dto.getDirector());
            movieRepository.save(movie);
            return mapper.map(movie,MovieResponseDto.class);
        }
        else {
            throw new RuntimeException("error");
        }
    }

     @CacheEvict(cacheNames = "movies",key = "#id")
    //@CacheEvict(cacheNames = "movies",allEntries = true )
    @Override
    public void deleteById(long id) {
        movieRepository.deleteById(id);
    }

}
