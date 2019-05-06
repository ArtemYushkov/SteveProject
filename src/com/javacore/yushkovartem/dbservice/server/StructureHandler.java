package com.javacore.yushkovartem.dbservice.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.Arrays;

public class StructureHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String filePath = "database/structure/test.xml";
        File file = new File(filePath);
        long length = file.length();
        httpExchange.getResponseHeaders().put("Content-Type:", Arrays.asList(new String[]{"text/xml"}));
        httpExchange.sendResponseHeaders(200, length);


        byte[] bytesArray = new byte[(int) file.length()];

        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray);
        fis.close();

        OutputStream os = httpExchange.getResponseBody();
        os.write(bytesArray);
        os.close();

    }
}
