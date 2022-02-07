package com.mymovieplan.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Role;
import com.mymovieplan.api.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.getById(id);
	}

}
