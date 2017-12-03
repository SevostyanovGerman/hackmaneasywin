package com.example.hackmaneasywindemo.controller;

import com.example.hackmaneasywindemo.IdGenerator;
import com.example.hackmaneasywindemo.UserDB;
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
import java.util.List;


@RestController
@CrossOrigin
public class RecognizeController {

	private static int count = 0;

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
		Long idClient = IdGenerator.getID();
		backgroundService.openVisualisation(idClient);
		String[] personInfo = RecognitionService.getPersonVkUrl();
		String vkUrl = personInfo[0];
		String photoUrl = personInfo[1];
		String id = vkUrl.substring(vkUrl.indexOf("id")+2);
		Client client = new Client(id, idClient);
		client.setVkPhotoUrl(photoUrl);
		if (count == 0) {
			client.setName("Герман");
			client.setVisitNumber(12);
			client.setWishes("горячий шоколад,шт,150.00,2");
		}
		userDao.addClient(client);
		count++;
		new Thread(() -> {
			try {
				backgroundService.loadClientAudios(client);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		System.out.println("я вернул Андрюхе " + client.getId());
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

	//отдача
	@GetMapping("/getPhotoUrl/{id}")
	public Client getPhotoUrl(@PathVariable("id") Long id) throws InterruptedException {
		Client client = userDao.getClientById(id);
		int count = 0;
		while (client == null) {
			client = userDao.getClientById(id);
			Thread.sleep(1000L);
			System.out.println("Жду уже " + count);
			count++;
		}
		System.out.println("ответ вернул");
		return client;
	}

	@GetMapping("/getAllClients")
	public List<Client> getAllClients() {
		return UserDB.clients;
	}
}
