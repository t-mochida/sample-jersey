package com.sample.jersey.web.resource.ex03;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sample.jersey.web.bean.ex03.UserData;
import com.sample.jersey.web.bean.ex03.UserResult;

@Path("/api/user")
public class User {

	@Inject
    private HttpServletRequest request;
	@Inject
    private HttpServletResponse response;

    @Path("/{userId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(@PathParam("userId") Integer userId, @Valid UserData user) {
    	System.out.println("userId=" + userId);

    	this.saveCookie(response, userId, user);

        UserResult result = new UserResult("OK", null);
    	return Response.ok(result).build();
    }

    /**
     * クッキーに保存
     * @param response
     * @param userId
     * @param user
     */
    private void saveCookie(HttpServletResponse response, Integer userId, UserData user) {
    	String csv = userId + "," + user.name + "," + user.diskUsage + "," + user.email;
        Cookie cookie = new Cookie("user-" + userId, csv);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    @Path("/{userId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadUser(@PathParam("userId") Integer userId) {
    	System.out.println("userId=" + userId);

    	UserData data = loadCookie(userId);
    	System.out.println(data);
    	if (data == null) {
    		return Response.status(Status.NOT_FOUND).build();
    	}
        UserResult result = new UserResult("OK", data);
    	return Response.ok(result).build();
    }

    /**
     * クッキーから読み込み
     * @param userId
     * @return
     */
    private UserData loadCookie(Integer userId) {
    	String value = null;
    	Cookie[] cookies = request.getCookies();
    	if (cookies == null) {
    		return null;
    	}
    	for (Cookie cookie : cookies) {
			if ( cookie.getName().equals("user-" + userId) ) {
				value = cookie.getValue();
				break;
			}
		}
    	if (value == null) {
    		return null;
    	}
    	System.out.println(value);
    	String[] values = value.split(",");
    	UserData data = new UserData(userId, values[1], values[2], values[3]);
    	return data;
    }
}
