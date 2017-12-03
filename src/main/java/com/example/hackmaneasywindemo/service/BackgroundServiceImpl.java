package com.example.hackmaneasywindemo.service;

import com.example.hackmaneasywindemo.UserDB;
import com.example.hackmaneasywindemo.dao.UserDao;
import com.example.hackmaneasywindemo.model.Client;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
		ChromeOptions options = new ChromeOptions();
		options.addArguments("no-sandbox");
		WebDriver driver = new ChromeDriver(options);
		Dimension dimension = new Dimension(850,1024);
		Point point1 = new Point(850,0);
		driver.manage().window().setPosition(point1);
		driver.manage().window().setSize(dimension);
		driver.get("http://e361497a.ngrok.io/photos/search/"+id);
	}
}
