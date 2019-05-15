package com.javacore.yushkovartem.webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ApiCriminalsHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        //check path
        URL url = new URL("http://localhost:6702/api/criminals");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream is = connection.getInputStream();
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        is.close();

        //READ TEMPLATE FILE
        File file = new File("webclient/snippets/criminaltablerow.html");
        byte[] fileBytes = null;
        if(file.exists()){
            fileBytes = Utils.readBytes("webclient/snippets/criminaltablerow.html");
        }

        String result = "";
        String raw = new String(bytes);
        String template = new String(fileBytes);

        String[] records = raw.split(";");
        for (String rec : records) {
            String values[] = rec.split(",");
            String html = new String(template);
            for (int i = 0, len = values.length; i < len; i++){
                html = html.replace("{{" + i + "}}", values[i]);
            }
            result += html;
        }


        httpExchange.getResponseHeaders().set("Content-Type", "text/plain");
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(result.getBytes());
        os.close();


    }
}
