package com.example.springbootredis.repository;


import com.example.springbootredis.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    @Query("SELECT m.id FROM Movie m")
    List<Long> getIds();
}
