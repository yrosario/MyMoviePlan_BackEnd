package com.mymovieplan.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Description;
import com.mymovieplan.api.repository.DescriptionRepository;

@Service
public class DescriptionServiceImpl implements DescriptionService {
	
	@Autowired
	private DescriptionRepository descriptionRepository;

	@Override
	public Description save(Description description) {
		return descriptionRepository.save(description);

	}

	@Override
	public Description findById(Long descId) {
		Description description = descriptionRepository.findById(descId).get();
		return description;
	}

}
