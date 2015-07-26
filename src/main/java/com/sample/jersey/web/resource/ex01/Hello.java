package com.sample.jersey.web.resource.ex01;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/hello")
public class Hello {

	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public String sayHello(){
		return "Hello, World!";
	}
}
