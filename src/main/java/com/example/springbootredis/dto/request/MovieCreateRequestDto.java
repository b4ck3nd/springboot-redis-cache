package com.example.springbootredis.dto.request;


import lombok.Data;

@Data
public class MovieCreateRequestDto {
    private String name;
    private String director;

}
