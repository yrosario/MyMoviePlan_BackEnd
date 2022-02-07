package com.mymovieplan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovieplan.api.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
