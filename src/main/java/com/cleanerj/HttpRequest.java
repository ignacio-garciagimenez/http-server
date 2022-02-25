package com.cleanerj;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private final String method;
    private final String version;
    private final String path;
    private final Map<String, String> headers;
    private final String body;

    private HttpRequest(String method, String version, String path, Map<String, String> headers, String body) {
        this.method = method;
        this.version = version;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }

    public String getMethod() {
        return method;
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.method)
            .append(" ")
            .append(this.path)
            .append(" ")
            .append(this.version).append("\n\r");

        for (String header : this.headers.keySet()) {
            builder.append(header).append(": ").append(this.headers.get(header)).append("\n\r");
        }

        builder.append("\n\r");
        builder.append(this.body);

        return builder.toString();
    }

    public static class Builder {

        private String method;
        private String version;
        private String path;
        private Map<String, String> headers = new HashMap<>();
        private String body;

        public Builder() {}

        public Builder withMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder withVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public Builder withHeader(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(method, version, path, headers, body);
        }
    }

}
