package com.example.hackmaneasywindemo.model;

import java.util.List;

public class Client {
	private String vkId;
	private String phoneNumber;
	private String age;
	private List<String> audios;

	public Client(String vkId) {
		this.vkId = vkId;
	}

	public Client(String vkId, String phoneNumber, String age) {
		this.vkId = vkId;
		this.phoneNumber = phoneNumber;
		this.age = age;
	}

	public String getVkId() {
		return vkId;
	}

	public void setVkId(String vkId) {
		this.vkId = vkId;
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

	public List<String> getAudios() {
		return audios;
	}

	public void setAudios(List<String> audios) {
		this.audios = audios;
	}
}
