package com.javacore.yushkovartem.db.server;

import com.javacore.yushkovartem.db.DBApplication;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;

public enum DBServer {
    INSTANCE;

    public static final int PORT = 6701;

    //сделать красиво

    public static final String BAD_HTML =
            "<html>" +
                "<head></head>" +
                    "<body><div style = \"background-color:#AAAA00; width: 100%; height:100%; border:1px solid black; color:white;\">{{state}}</div></body>" +
            "</html>";

    public void start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 10);
        server.createContext("/db/state", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String state = BAD_HTML.replace("{{state}}", DBApplication.INSTANCE.getStateName());
                httpExchange.sendResponseHeaders(200, state.length());
                httpExchange.getResponseHeaders().put("Content-Type:", Arrays.asList(new String[]{"text/html"}));
                OutputStream os = httpExchange.getResponseBody();
                os.write(state.getBytes());
                os.close();
            }
        });

        server.createContext("/db/structure", new StructureHandler());
        server.start();
        String message = String.format("Server is running on port: %d", server.getAddress().getPort());
        System.out.println(message);
    }
}
