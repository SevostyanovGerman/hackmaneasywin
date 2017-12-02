package com.example.hackmaneasywindemo.controller;

import com.example.hackmaneasywindemo.dao.UserDao;
import com.example.hackmaneasywindemo.model.Client;
import com.example.hackmaneasywindemo.service.BackgroundService;
import com.example.hackmaneasywindemo.service.PhotoService;
import com.example.hackmaneasywindemo.service.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RecognizeController {


	@Autowired
	private BackgroundService backgroundService;

	@Autowired
	private UserDao userDao;

	@GetMapping("/processClient")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void processClient() throws IOException, InterruptedException {
		PhotoService.takePhoto();
		String vkUrl = RecognitionService.getPersonVkUrl();
		String id = vkUrl.substring(vkUrl.indexOf("id"+2));
		Client client = new Client(id);
		userDao.addClient(client);
		backgroundService.loadClientAudios(client);
		System.out.println(vkUrl);
	}


}
