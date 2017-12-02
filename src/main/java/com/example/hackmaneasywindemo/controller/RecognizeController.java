package com.example.hackmaneasywindemo.controller;

import com.example.hackmaneasywindemo.service.PhotoService;
import com.example.hackmaneasywindemo.service.RecognitionService;
import com.github.sarxos.webcam.Webcam;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class RecognizeController {


	@GetMapping("/processClient")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void processClient() throws IOException {
		PhotoService.takePhoto();
		String vkUrl = RecognitionService.getPersonVkUrl();
		System.out.println(vkUrl);
	}


}
