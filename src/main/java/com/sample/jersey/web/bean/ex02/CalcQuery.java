package com.sample.jersey.web.bean.ex02;

import javax.ws.rs.QueryParam;

public class CalcQuery {
	@QueryParam("a")
	public int a;

	@QueryParam("b")
	public int b;
}
