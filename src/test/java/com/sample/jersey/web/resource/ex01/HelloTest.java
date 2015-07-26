package com.sample.jersey.web.resource.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloTest extends JerseyTest {
	@Override
    protected Application configure() {
        return new ResourceConfig(Hello.class);
    }

	@Before
	public void setUpChild() {}

	@After
	public void tearDownChild() {}

	@Test
	public void test() {
		String response = target("/api/hello").request().get(String.class);
        System.out.println(response);

        assertThat(response, is("Hello, World!"));
	}

}
