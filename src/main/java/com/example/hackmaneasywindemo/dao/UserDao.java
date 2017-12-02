package com.example.hackmaneasywindemo.dao;

import com.example.hackmaneasywindemo.UserDB;
import com.example.hackmaneasywindemo.model.Client;

public interface UserDao {
	void addClient(Client client);
	void updateClient(Client client);
}
