package com.example.hackmaneasywindemo.model;

public class Client {
	private String vkUrl;
	private String phoneNumber;
	private String age;

	public Client(String vkUrl) {
		this.vkUrl = vkUrl;
	}

	public Client(String vkUrl, String phoneNumber, String age) {
		this.vkUrl = vkUrl;
		this.phoneNumber = phoneNumber;
		this.age = age;
	}

	public String getVkUrl() {
		return vkUrl;
	}

	public void setVkUrl(String vkUrl) {
		this.vkUrl = vkUrl;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
