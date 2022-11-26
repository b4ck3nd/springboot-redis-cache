package com.example.springbootredis.config;


import com.example.springbootredis.dto.request.MovieCreateRequestDto;
import com.example.springbootredis.dto.request.MovieUpdateRequestDto;
import com.example.springbootredis.dto.response.MovieResponseDto;
import com.example.springbootredis.model.Movie;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration()
                .setSkipNullEnabled(false)
                .setAmbiguityIgnored(false)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        mapper.createTypeMap(Movie.class, MovieResponseDto.class)
                .addMapping(Movie::getName,MovieResponseDto::setName)
                .addMapping(Movie::getDirector,MovieResponseDto::setDirector)
                .addMapping(Movie::getId,MovieResponseDto::setId);

        mapper.createTypeMap(MovieCreateRequestDto.class,Movie.class)
                .addMapping(MovieCreateRequestDto::getName,Movie::setName)
                .addMapping(MovieCreateRequestDto::getDirector,Movie::setDirector);
        mapper.createTypeMap(MovieUpdateRequestDto.class,Movie.class)
                .addMapping(MovieUpdateRequestDto::getDirector,Movie::setDirector)
                .addMapping(MovieUpdateRequestDto::getName,Movie::setName);
        return mapper;
    }
}
