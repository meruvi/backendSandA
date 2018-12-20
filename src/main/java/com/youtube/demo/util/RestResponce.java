package com.youtube.demo.util;

public class RestResponce {
	private Integer responceCode;
	private String message;
	
	public RestResponce(Integer responceCode) {
		super();
		this.responceCode = responceCode;
	}
	
	public RestResponce(Integer responceCode, String message) {
		super();
		this.responceCode = responceCode;
		this.message = message;
	}
	
	public Integer getResponceCode() {
		return responceCode;
	}
	public void setResponceCode(Integer responceCode) {
		this.responceCode = responceCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
