package com.example.hackmaneasywindemo.service;

import com.example.hackmaneasywindemo.model.Client;

public interface MusicService {
	void startMusic(Client client, Integer playNumber);
	void stopMusic();

}
