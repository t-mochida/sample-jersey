package com.sample.jersey.web.bean.ex04;

public class ImageResult {
	public String path;

	public ImageResult() {}

	public ImageResult(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("path=").append(path);
		return builder.toString();
	}
}
