package com.example.hackmaneasywindemo.dao;

import com.example.hackmaneasywindemo.UserDB;
import com.example.hackmaneasywindemo.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
	public void addClient(Client client) {
		UserDB.clients.add(client);
	}

	public void updateClientAudio(Client client) {
		for (Client currClient : UserDB.clients) {
			if (currClient.getVkId().equals(client.getVkId())) {
				currClient.setAudios(client.getAudios());
			}
		}
	}

	public Client getClientById(Long id) {
		for (Client currClient : UserDB.clients) {
			if (currClient.getId().equals(id)) {
				return currClient;
			}
		}
		return null;
	}
}
