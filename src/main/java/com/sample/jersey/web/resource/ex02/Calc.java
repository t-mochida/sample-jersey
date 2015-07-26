package com.sample.jersey.web.resource.ex02;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sample.jersey.web.bean.ex02.CalcParam;
import com.sample.jersey.web.bean.ex02.CalcQuery;
import com.sample.jersey.web.bean.ex02.CalcResult;

@Path("/api/calc")
public class Calc {

	@Path("/add")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CalcResult calcAdd(@QueryParam("a")int a, @QueryParam("b")int b) {
		return new CalcResult(a, b, a + b);
	}

	@Path("/sub")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CalcResult calcSub(@BeanParam CalcQuery query) {
		return new CalcResult(query.a, query.b, query.a - query.b);
	}

	@Path("/mul")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CalcResult calcMul(CalcParam param) {
		return new CalcResult(param.a, param.b, param.a * param.b);
	}

	@Path("/div")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response calcDiv(@QueryParam("a")int a, @QueryParam("b")int b) {
		int r = 0;
		try {
			r = a / b;
		} catch (ArithmeticException e) {//　0で割ると発生
			return Response.status(Status.BAD_REQUEST).entity("zero divide.").build();
		}
		return Response.ok(new CalcResult(a, b, r)).build();
	}
}
