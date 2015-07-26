package com.sample.jersey.web.bean.ex03;

public class UserResult {
	public String msg;
	public UserData data;

	public UserResult() {}

	public UserResult(String msg, UserData data) {
		this.msg = msg;
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("msg=").append(msg).append(", data=").append(data);
		return builder.toString();
	}


}
