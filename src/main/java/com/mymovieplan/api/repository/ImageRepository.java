package com.mymovieplan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovieplan.api.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
