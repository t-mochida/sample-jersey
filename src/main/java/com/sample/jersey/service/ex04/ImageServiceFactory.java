package com.sample.jersey.service.ex04;

import org.glassfish.hk2.api.Factory;

public class ImageServiceFactory implements Factory<ImageService> {
	@Override
	public void dispose(ImageService service) { }

	@Override
	public ImageService provide() {
		return new ImageServiceImpl();
	}

}
