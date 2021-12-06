package com.mymovieplan.api.service;

import com.mymovieplan.api.model.Description;

public interface DescriptionService {

	public Description save(Description description);

	public Description findById(Long descId);
	
	

}
