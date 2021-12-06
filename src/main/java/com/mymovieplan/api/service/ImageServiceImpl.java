package com.mymovieplan.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Image;
import com.mymovieplan.api.repository.ImageRepository;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public Image save(Image image) {
		return imageRepository.save(image);
	}

}
