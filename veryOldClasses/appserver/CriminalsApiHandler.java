package com.javacore.yushkovartem.appserver;

import com.javacore.yushkovartem.webservice.WebClientApplication;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CriminalsApiHandler implements HttpHandler {

    public static final String LIST_REQUEST = "^/api/criminals/$"; //"/criminals"
    public static final String PERSON_REQUEST = "^/api/criminals/([0-9]+)$"; //"/criminals/{n}"

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String dbResult = requestDB();
        httpExchange.getResponseHeaders().put("Content-Type", Arrays.asList(new String[]{"text/plain"}));
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();

        if (path.matches(LIST_REQUEST)) {
            response = "LIST";
            os.write(response.getBytes());
            //httpExchange.sendResponseHeaders(200, 0);
        } else if (path.matches(PERSON_REQUEST)){
            response = "PERSON";
            os.write(response.getBytes());
            //httpExchange.sendResponseHeaders(200, 0);
        } else {
            httpExchange.sendResponseHeaders(400, 0);
        }
        os.close();
    }

    private String requestDB() throws Exception {
        URL url = new URL ("http://localhost:6701/api/query");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

    }
}
