package com.javacore.yushkovartem.db.server;

import com.javacore.yushkovartem.db.DBApplication;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import sun.misc.IOUtils;

import java.io.*;
import java.util.Arrays;

public class StructureHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String filePath = "/Users/zstudent/IdeaProjects/Steve/database/structure/test.xml";
        httpExchange.getResponseHeaders().put("Content-Type:", Arrays.asList(new String[]{"text/xml"}));

        File file = new File(filePath);

        byte[] bytesArray = new byte[(int) file.length()];

        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray); //read file into bytes[]
        fis.close();


        OutputStream os = httpExchange.getResponseBody();

        os.write(bytesArray);


        os.close();

    }
}
