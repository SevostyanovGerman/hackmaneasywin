package com.example.hackmaneasywindemo.service;

import com.example.hackmaneasywindemo.model.Client;

public interface BackgroundService {
	void loadClientAudios(Client client) throws InterruptedException;
	void openVisualisation(Long id);
}
