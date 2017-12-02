package com.example.hackmaneasywindemo;

public class IdGenerator {
	private static Long lastId = 0L;

	public static synchronized Long getID() {
		return lastId++;
	}
}
