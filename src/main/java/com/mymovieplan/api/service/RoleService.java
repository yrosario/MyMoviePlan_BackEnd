package com.mymovieplan.api.service;

import com.mymovieplan.api.model.Role;


public interface RoleService {
	
	public Role save(Role role);
	public Role findById(Long id);

}
