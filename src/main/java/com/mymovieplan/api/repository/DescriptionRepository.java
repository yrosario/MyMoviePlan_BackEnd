package com.mymovieplan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovieplan.api.model.Description;

public interface DescriptionRepository extends JpaRepository<Description, Long> {

}
