package com.sample.jersey.web.resource.ex04;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sample.jersey.service.ex04.ImageService;
import com.sample.jersey.web.bean.ex04.ImageResult;

public class ImageTest extends JerseyTest {
	@Override
    protected Application configure() {
        return new ResourceConfig(Image.class)
        .register(JacksonFeature.class)
        .register(MultiPartFeature.class)
        .register(new AbstractBinder() {
            @Override
            protected void configure() {
            	bindFactory(ImageServiceMockFactory.class).to(ImageService.class);
            }
        });
    }

	public static class ImageServiceMockFactory implements Factory<ImageService> {
		@Override
		public void dispose(ImageService request) { }

		@Override
		public ImageService provide() {
			ImageService service = mock(ImageService.class);
			when(service.writeToFile(anyObject(), anyObject(), anyObject(), anyObject())).thenReturn("test.png");
			return service;
		}
	}

	@Override
    protected void configureClient(ClientConfig config) {
        config.register(MultiPartFeature.class);
    }

	@Before
	public void setUpChild() {}

	@After
	public void tearDownChild() {}

	@Test
	public void upload() throws IOException {
		String baseDir = System.getProperty("user.dir");
		String imgPath = String.format("%s/src/main/webapp/img/noimage.png", baseDir);
		final File imgFile = new File(imgPath);
		System.out.println( imgFile.getAbsoluteFile() );

		final FormDataMultiPart form = new FormDataMultiPart();
		final FormDataBodyPart type = new FormDataBodyPart("img-type", "image/png");
		final FormDataBodyPart size = new FormDataBodyPart("img-size", "1532");
		final FormDataBodyPart file = new FormDataBodyPart(
				FormDataContentDisposition.name("img-file").fileName("noimage.png").build(),
				imgFile,
				MediaType.APPLICATION_OCTET_STREAM_TYPE);
		form.bodyPart(type);
		form.bodyPart(size);
		form.bodyPart(file);

		ImageResult response = target("/api/image")
				.request(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.MULTIPART_FORM_DATA), ImageResult.class);
        System.out.println(response);

        ImageResult expected = new ImageResult("test.png");
        assertThat(response.toString(), is(expected.toString()));
	}
}
