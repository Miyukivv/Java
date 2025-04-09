package com.example.PixelGuardApplication;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private BufferedReader reader;
    private PrintWriter out;
    private Socket socket;

    public ClientHandler(Socket socket, BufferedReader reader, PrintWriter out) {
        this.socket = socket;
        this.reader = reader;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            String message;

            while ((message = reader.readLine()) != null) {
                System.out.println("Message received");

                System.out.println(message);

                String[] messageParts = message.split(" ");


                if (messageParts.length == 2 && messageParts[0].equals("ban") && isInteger(messageParts[1])) {
                    int token = Integer.parseInt(messageParts[1]);

                    synchronized (this) {
                        int numberOfDeletedRecords = AdministratorUtills.ban(token);
                        out.println(String.format("Deleted %d records", numberOfDeletedRecords));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
