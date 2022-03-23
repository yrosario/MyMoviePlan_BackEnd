package com.mymovieplan.api.service;

import java.util.List;

import com.mymovieplan.api.model.Role;


public interface RoleService {
	
	public Role save(Role role);
	public Role findById(Long id);
	public List<Role> findAll();

}
