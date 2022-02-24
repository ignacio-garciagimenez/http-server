package com.cleanerj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {       

        try {
            ServerSocket server = new ServerSocket(8080);
            System.out.println("Listening on port 8080...");
            
            while(true) {
                Socket client = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line;
                
                while ((line = in.readLine()) != null && !line.isEmpty() || !client.isConnected()) {
                    System.out.println(line);                    
                }
                System.out.println("Ended");
                client.close();
            }
        } catch (IOException e) {
            System.out.println("For sure is in use");
            e.printStackTrace();
        } finally {
        }

    }
}
