package com.example.hackmaneasywindemo.dao;

import com.example.hackmaneasywindemo.UserDB;
import com.example.hackmaneasywindemo.model.Client;

public interface UserDao {
	void addClient(Client client);

	void updateClientAudio(Client client);

	Client getClientById(Long id);
}
