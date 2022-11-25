package com.example.springbootredis.controller;


import com.example.springbootredis.dto.request.MovieCreateRequestDto;
import com.example.springbootredis.dto.request.MovieUpdateRequestDto;
import com.example.springbootredis.dto.response.MovieResponseDto;
import com.example.springbootredis.model.Movie;
import com.example.springbootredis.service.MovieService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {
    private final @NonNull MovieService movieService;

    @GetMapping("/")
    public ResponseEntity<List<MovieResponseDto>> findAll() {
        List<MovieResponseDto> all = movieService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<MovieResponseDto> add(@RequestBody MovieCreateRequestDto dto) {
        MovieResponseDto add = movieService.add(dto);
        return new ResponseEntity<>(add,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> findById(@PathVariable long id) {
        MovieResponseDto byId = movieService.findById(id);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MovieResponseDto> updateMovie(@RequestBody MovieUpdateRequestDto dto) {
        MovieResponseDto movieResponseDto = movieService.updateMovie(dto);
        return new ResponseEntity<>(movieResponseDto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        movieService.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

}
