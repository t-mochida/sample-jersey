package com.sample.jersey.web.resource.ex05;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/csv")
public class Csv {

	@GET
	@Path("/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response download() {
		StringBuilder sb = new StringBuilder();
		sb.append("s1,s2,s3");

		InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
		return Response.ok(is).header("Content-disposition", "attachment; filename=data.csv").build();
	}
}
