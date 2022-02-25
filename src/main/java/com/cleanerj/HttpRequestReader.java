package com.cleanerj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpRequestReader {

    private final BufferedReader reader;
    private final HttpRequest.Builder requestBuilder = new HttpRequest.Builder();
    
    public HttpRequestReader(InputStream requestStream) {
        this.reader = new BufferedReader(new InputStreamReader(requestStream));
    }

    public HttpRequest read() throws IOException {
        this.readStartLine();
        this.readHeaders();           
        //this.readBody();

        return this.requestBuilder.build();
    }

    private void readBody() throws IOException {
        StringBuilder bodyBuilder = new StringBuilder();
        
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            bodyBuilder.append(line);
        }
        this.requestBuilder.withBody(bodyBuilder.toString());
    }

    private void readHeaders() throws IOException {        
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] currentHeader = line.split(":");
            this.requestBuilder.withHeader(currentHeader[0].trim(), currentHeader[1].trim());
        }
    }

    private void readStartLine() throws IOException {
        String line = this.reader.readLine();
        if (line == null) throw new IOException("Request had no start line!");
        
        String[] startLine = line.split(" ");
        this.requestBuilder.withMethod(startLine[0])
            .withPath(startLine[1])
            .withVersion(startLine[2]);
    }
}
