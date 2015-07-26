package com.sample.jersey.web.resource.ex03;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sample.jersey.web.bean.ex03.UserData;
import com.sample.jersey.web.bean.ex03.UserResult;

public class UserTest extends JerseyTest {
	@Override
    protected Application configure() {
        return new ResourceConfig(User.class)
        .register(JacksonFeature.class)
        .register(new AbstractBinder() {
            @Override
            protected void configure() {
            	bindFactory(RequestFactory.class).to(HttpServletRequest.class);
            	bindFactory(ResponseFactory.class).to(HttpServletResponse.class);
            }
        });
    }

	public static class RequestFactory implements Factory<HttpServletRequest> {

		@Override
		public void dispose(HttpServletRequest request) { }

		@Override
		public HttpServletRequest provide() {
			HttpServletRequest request = mock(HttpServletRequest.class);
			Cookie[] cookies = {new Cookie("user-1", "1,test1ro,33.33,test@sample.com")};
			when(request.getCookies()).thenReturn(cookies);
			return request;
		}

	}

	public static class ResponseFactory implements Factory<HttpServletResponse> {

		private static HttpServletResponse response;

		@Override
		public void dispose(HttpServletResponse response) {	}

		@Override
		public HttpServletResponse provide() {
			response = mock(HttpServletResponse.class);
			return response;
		}

		public static void verifyAddCookie() {
			verify(response).addCookie( anyObject() );
		}
	}


	@Before
	public void setUpChild() {}

	@After
	public void tearDownChild() {}

	@Test
	public void save() {

        UserData data = new UserData(1, "test1ro", "33.33", "test@sample.com");

        UserResult response = target("/api/user/1")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(data, MediaType.APPLICATION_JSON_TYPE), UserResult.class);
        System.out.println(response);

        UserResult expected = new UserResult("OK", null);
        assertThat(response.toString(), is(expected.toString()));
        ResponseFactory.verifyAddCookie();
   	}

	@Test
	public void load() {
		UserResult response = target("/api/user/1")
                .request(MediaType.APPLICATION_JSON).get(UserResult.class);
        System.out.println(response);

        UserResult expected = new UserResult("OK", new UserData(1, "test1ro", "33.33", "test@sample.com"));
        assertThat(response.toString(), is(expected.toString()));
	}
}
