package com.sample.jersey.service.ex04;

import java.io.InputStream;

public interface ImageService {

	public String writeToFile(InputStream inputStream, String type, Long size, String fileName);
}
