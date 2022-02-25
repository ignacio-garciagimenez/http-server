package com.cleanerj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening on port 8080...");
        
        try {
            while (true) {
                Socket client = server.accept();
                executorService.execute(new HttpRunner(client));
            }

        } catch (IOException e) {
            System.out.println("For sure is in use");
            e.printStackTrace();
        } finally {
            server.close();
        }

    }
}
