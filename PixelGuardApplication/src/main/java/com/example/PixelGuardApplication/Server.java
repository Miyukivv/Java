package com.example.PixelGuardApplication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private String login = "admin";
    private String password = "admin123";
    private BufferedReader reader;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        boolean isConnected = false;
        while (true) {
            Socket clientSocket;
            try {
                if (!isConnected) {
                    clientSocket = serverSocket.accept();

                    OutputStream output = clientSocket.getOutputStream();
                    PrintWriter pw = new PrintWriter(output, true);

                    InputStream input = clientSocket.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));

                    pw.println("Enter login:");
                    String userName = reader.readLine();

                    pw.println("Enter password:");
                    String userPassword = reader.readLine();

                    if (userName.equals(login) && userPassword.equals(password)) {
                        pw.println("You are now connected.");
                        isConnected = true;
                        ClientHandler clientHandler = new ClientHandler(clientSocket, reader, pw);
                        clientHandler.run();
                    } else {
                        pw.println("Incorrect credentials provided.");
                        clientSocket.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
