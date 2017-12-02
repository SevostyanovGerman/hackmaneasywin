package com.example.hackmaneasywindemo.dao;

import com.example.hackmaneasywindemo.UserDB;
import com.example.hackmaneasywindemo.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	public void addClient(Client client) {
		UserDB.clients.add(client);
	}

	public void updateClient(Client client) {
		for (Client currClient : UserDB.clients) {
			if (currClient.getVkId().equals(client.getVkId())) {
				UserDB.clients.remove(currClient);
				UserDB.clients.add(client);
			}
		}
	}
}
