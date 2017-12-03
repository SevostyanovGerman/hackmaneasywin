package com.example.hackmaneasywindemo.service;

import com.example.hackmaneasywindemo.UserDB;
import com.example.hackmaneasywindemo.dao.UserDao;
import com.example.hackmaneasywindemo.model.Client;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BackgroundServiceImpl implements BackgroundService {

	@Autowired
	private UserDao userDao;

	@Override
	@Async
	public void loadClientAudios(Client client) throws InterruptedException {
		List<String> audios = Arrays.asList(RecognitionService.getPersonAudio(client.getVkId()));
		client.setAudios(audios);
		userDao.updateClientAudio(client);
	}

	@Override
	public void openVisualisation(Long id) {
		WebDriver driver = new ChromeDriver();
		driver.get("http://e361497a.ngrok.io/photos/search/"+id);
	}
}
