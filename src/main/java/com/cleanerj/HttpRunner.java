package com.cleanerj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpRunner implements Runnable {

    private final Socket client;

    public HttpRunner(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            HttpRequest request = new HttpRequestReader(client.getInputStream()).read();
            System.out.println(request);

            // Write back to client
            BufferedWriter responseWriter = new BufferedWriter(
                    new OutputStreamWriter(this.client.getOutputStream()));

            responseWriter.append("HTTP/1.1 200\n");
            responseWriter.append("Content-Type: text/html\n");
            responseWriter.append("\n");
            responseWriter.flush();
            responseWriter.append("<html><body><h1>Hello from CleanerJ's http server!</h1></body></html>");
            responseWriter.flush();

            System.out.println("Response ended");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
