package com.example.PixelGuardApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PixelGuardApplication {

	public static void main(String[] args) {
		// Load image from the database on startup
		ImageRGB imageRGB = ImageRGB.getInstance();
		imageRGB.setImageBasedOnPixels();

		startAdminServer(5000);

		SpringApplication.run(PixelGuardApplication.class, args);

		System.out.println("PixelGuard server is up and running!");
		System.out.println("API: http://localhost:8080/");
		System.out.println("Image: http://localhost:8080/image");
		System.out.println("Register token: POST http://localhost:8080/register");
		System.out.println("Set pixel: POST http://localhost:8080/pixel");
		System.out.println("Admin socket listening on port 5000");
	}

	private static void startAdminServer(int port) {
		Server server = new Server(port);
		server.setDaemon(true);
		server.start();
	}
}
