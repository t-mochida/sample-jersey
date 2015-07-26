package com.sample.jersey.service.ex04;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sample.jersey.service.ex04.ImageService;

public class ImageServiceImpl implements ImageService {

	@Override
	public String writeToFile(InputStream inputStream, String type, Long size, String fileName) {
		String path = System.getProperty("java.io.tmpdir") + File.separator + fileName;

		try {
			OutputStream out = new FileOutputStream(new File(path));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
