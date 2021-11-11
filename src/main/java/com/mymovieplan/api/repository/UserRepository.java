package com.mymovieplan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovieplan.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
