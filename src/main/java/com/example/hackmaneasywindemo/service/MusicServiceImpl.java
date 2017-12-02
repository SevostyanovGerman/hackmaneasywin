package com.example.hackmaneasywindemo.service;

import com.example.hackmaneasywindemo.model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MusicServiceImpl implements MusicService {

	@Override
	public void startMusic(Client client, Integer playNumber) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity("http://localhost:8081/id" + client.getVkId() + "/play/" + playNumber, String[].class);
	}

	@Override
	public void stopMusic() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity("http://localhost:8081/stop", String[].class);

	}
}
