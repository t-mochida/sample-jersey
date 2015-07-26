package com.sample.jersey.web.bean.ex02;

public class CalcResult {
	public int a;
	public int b;
	public int result;

	public CalcResult(){}

	public CalcResult(int a, int b, int result) {
		this.a = a;
		this.b = b;
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("a=").append(a)
			.append(", b=").append(b)
			.append(", result=").append(result);
		return builder.toString();
	}


}
