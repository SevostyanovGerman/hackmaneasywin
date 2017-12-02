package com.example.hackmaneasywindemo.controller;

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


	@GetMapping("/takePhoto")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void takePhoto() throws IOException {
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(640, 480));
		webcam.open();

		// get image
		BufferedImage image = webcam.getImage();

		// save image to PNG file
		ImageIO.write(image, "PNG", new File("test.png"));

		System.out.println("photo done");
	}
}
