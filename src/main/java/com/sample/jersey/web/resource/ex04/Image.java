package com.sample.jersey.web.resource.ex04;

import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.sample.jersey.service.ex04.ImageService;
import com.sample.jersey.web.bean.ex04.ImageResult;


@Path("/api/image")
public class Image {

	@Inject
	private ImageService imageService;

	@POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ImageResult upload(
    		@FormDataParam("img-type") String type,
    		@FormDataParam("img-size") Long size,
    		@FormDataParam("img-file") InputStream inputStream,
    		@FormDataParam("img-file") FormDataContentDisposition file) {
		System.out.println("filename=" + file.getFileName() + ", type=" + type + ", size=" + size);

		String path = imageService.writeToFile(inputStream, type, size, file.getFileName());

		ImageResult result = new ImageResult(path);
		return result;
	}
}
