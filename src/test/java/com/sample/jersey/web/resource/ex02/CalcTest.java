package com.sample.jersey.web.resource.ex02;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import junit.framework.AssertionFailedError;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sample.jersey.web.bean.ex02.CalcParam;
import com.sample.jersey.web.bean.ex02.CalcResult;

public class CalcTest extends JerseyTest {
	@Override
    protected Application configure() {
        return new ResourceConfig(Calc.class).register(JacksonFeature.class);
    }

	@Before
	public void setUpChild() {}

	@After
	public void tearDownChild() {}

	@Test
	public void calcAdd() {
		CalcResult response = target("/api/calc/add")
				.queryParam("a", 5)
                .queryParam("b", 2)
                .request(MediaType.APPLICATION_JSON).get(CalcResult.class);
        System.out.println(response);

        CalcResult expected = new CalcResult(5, 2, 5+2);
        assertThat(response.toString(), is(expected.toString()));
	}

	@Test
	public void calcAdd_Error() {
		try {
			CalcResult response = target("/api/calc/add")
					.queryParam("a", "a")
	                .queryParam("b", 2)
	                .request(MediaType.APPLICATION_JSON).get(CalcResult.class);
			System.out.println(response);

			fail("例外を発生しませんでした");
		} catch (NotFoundException e) {
			System.out.println(e);
		}
	}

	@Test
	public void calcSub() {
		CalcResult response = target("/api/calc/sub")
				.queryParam("a", 5)
                .queryParam("b", 2)
                .request(MediaType.APPLICATION_JSON).get(CalcResult.class);
        System.out.println(response);

        CalcResult expected = new CalcResult(5, 2, 5-2);
        assertThat(response.toString(), is(expected.toString()));
	}


	@Test
	public void calcMul() {
		CalcParam param = new CalcParam();
		param.a = 3;
		param.b = 8;

		CalcResult response = target("/api/calc/mul")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(param, MediaType.APPLICATION_JSON_TYPE), CalcResult.class);
        System.out.println(response);

        CalcResult expected = new CalcResult(3, 8, 3*8);
        assertThat(response.toString(), is(expected.toString()));
	}

	@Test
	public void calcDiv() {
		CalcResult response = target("/api/calc/div")
				.queryParam("a", 8)
                .queryParam("b", 2)
                .request(MediaType.APPLICATION_JSON).get(CalcResult.class);
        System.out.println(response);

        CalcResult expected = new CalcResult(8, 2, 8/2);
        assertThat(response.toString(), is(expected.toString()));
	}

	@Test
	public void calcDiv_Error() {
		try {
			CalcResult response = target("/api/calc/div")
					.queryParam("a", 8)
	                .queryParam("b", 0)
	                .request(MediaType.APPLICATION_JSON).get(CalcResult.class);
	        System.out.println(response);

	        fail("例外を発生しませんでした");
		} catch (BadRequestException e) {
			System.out.println(e);
		}
	}
}
