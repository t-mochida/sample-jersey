package com.sample.jersey.web.resource.ex05;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CsvTest extends JerseyTest {
	@Override
    protected Application configure() {
        return new ResourceConfig(Csv.class).register(JacksonFeature.class);
    }

	@Before
	public void setUpChild() {}

	@After
	public void tearDownChild() {}

	@Test
	public void download() {
		String response = target("/api/csv/download").request().get(String.class);
        System.out.println(response);

        String expected = "s1,s2,s3";
        assertThat(response, is(expected));
	}
}
