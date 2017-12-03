package com.example.hackmaneasywindemo.model;

import java.util.List;

public class Client {
	private Long id;
	private String name;
	private int visitNumber;
	private String wishes;
	private String vkId;
	private String phoneNumber;
	private String age;
	private List<String> audios;
	private String vkPhotoUrl;

	public Client(String vkId, Long id) {
		this.vkId = vkId;
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVkPhotoUrl() {
		return vkPhotoUrl;
	}

	public void setVkPhotoUrl(String vkPhotoUrl) {
		this.vkPhotoUrl = vkPhotoUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVisitNumber() {
		return visitNumber;
	}

	public void setVisitNumber(int visitNumber) {
		this.visitNumber = visitNumber;
	}

	public String getWishes() {
		return wishes;
	}

	public void setWishes(String wishes) {
		this.wishes = wishes;
	}
}
