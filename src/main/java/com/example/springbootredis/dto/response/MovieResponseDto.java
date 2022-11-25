package com.example.springbootredis.dto.response;


import lombok.Data;

@Data
public class MovieResponseDto {
    private long id;
    private String name;
    private String director;

}
