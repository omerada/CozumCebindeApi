package com.omerada.cozumcebinde.core.payload;

public class RedirectUrlResponse {

	private String url;
	private String rawBody;

	public RedirectUrlResponse(String url, String rawBody) {
		this.url = url;
		this.rawBody = rawBody;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRawBody() {
		return rawBody;
	}

	public void setRawBody(String rawBody) {
		this.rawBody = rawBody;
	}
}