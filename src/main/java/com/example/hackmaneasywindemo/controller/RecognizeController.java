package com.example.hackmaneasywindemo.controller;

import com.example.hackmaneasywindemo.IdGenerator;
import com.example.hackmaneasywindemo.dao.UserDao;
import com.example.hackmaneasywindemo.model.Client;
import com.example.hackmaneasywindemo.service.BackgroundService;
import com.example.hackmaneasywindemo.service.MusicService;
import com.example.hackmaneasywindemo.service.PhotoService;
import com.example.hackmaneasywindemo.service.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class RecognizeController {


	@Autowired
	private BackgroundService backgroundService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private MusicService musicService;

	@GetMapping("/processClient")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Long processClient() throws IOException, InterruptedException {
		PhotoService.takePhoto();
		String vkUrl = RecognitionService.getPersonVkUrl();
		String id = vkUrl.substring(vkUrl.indexOf("id")+2);
		Client client = new Client(id, IdGenerator.getID());
		userDao.addClient(client);
		new Thread(() -> {
			try {
				backgroundService.loadClientAudios(client);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		return client.getId();
	}

	@GetMapping("/startMusic/{id}")
	public void startMusic(@PathVariable("id") Long id) {
		musicService.startMusic(userDao.getClientById(id), 1);
	}

	@GetMapping("/stopMusic")
	public void stopMusic() {
		musicService.stopMusic();
	}


}
