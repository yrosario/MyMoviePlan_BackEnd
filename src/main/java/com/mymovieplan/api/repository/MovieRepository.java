package com.mymovieplan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovieplan.api.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
