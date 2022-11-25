package com.example.springbootredis.dto.request;


import lombok.Data;

@Data
public class MovieUpdateRequestDto {
    private long id;
    private String name;
    private String director;
}
