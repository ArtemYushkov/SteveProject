package com.javacore.yushkovartem.webservice;

import com.javacore.yushkovartem.dbservice.data.TableRow;
import com.javacore.yushkovartem.dbservice.dbstate.DBStateInit;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;

public class CommonHttpHandler implements HttpHandler {

    public static final String HTML_FORMAT = "^/pages/(([a-zA-Z]+\\.)(html))$";
    public static final String CSS_FORMAT = "^/css/(([a-zA-Z]+\\.)(css))$";
    public static final String SCBID_FORMAT = "^/api/show[cC]riminal[bB]y[Ii]d/[0-9]+$";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        byte[] fileBytes = null;
        String result = null;
        String response = "";

        if (path.matches(HTML_FORMAT)) {
            response = path;
            File file = new File("webclient" + path);
            response += "<br>File Exists: " + file.exists();

            if (file.exists()) {
                fileBytes = Utils.readBytes("webclient" + path);
            }

            result = new String(fileBytes);
            result = result.replace("{{time}}", LocalTime.now().toString());

            httpExchange.getResponseHeaders().set("Content-Type", "text/html");



        } else if (path.matches(CSS_FORMAT)) {
            response = path;
            File file = new File("webclient/static" + path);

            if (file.exists()) {
                fileBytes = Utils.readBytes("webclient/static" + path);
            }

            httpExchange.getResponseHeaders().set("Content-Type", "text/css");
            result = new String(fileBytes);
        } else if (path.matches(SCBID_FORMAT)){
            int id;
            TableRow criminal;
            String idString = path.split("/")[3];
            System.out.println(idString);
            id = Integer.parseInt(idString);
            criminal = DBStateInit.getTable().getRow(id);

            fileBytes = Utils.readBytes("webclient/pages/criminals.html");
            result = new String(fileBytes);

            StringBuilder test = new StringBuilder();

            result = result.replace("<th>ID</th>", "<th>ID : " + id + "</th>");
            result = result.replace("<th>firstName</th>", "<th>firstName :" + criminal.getValues().get(1) + "</th>");
            result = result.replace("<th>lastName</th>", "<th>lastName :" + criminal.getValues().get(2) + "</th>");
            result = result.replace("<th>nickname</th>", "<th>nickname :" + criminal.getValues().get(3) + "</th>");
            result = result.replace("<th>criminalFamilyId</th>", "<th>criminalFamilyId :" + criminal.getValues().get(4) + "</th>");
            result = result.replace("<th>dateOfBirth</th>", "<th>dateOfBirth :" + criminal.getValues().get(5) + "</th>");
            result = result.replace("<th>deceased</th>", "<th>deceased :" + criminal.getValues().get(6) + "</th>");
            result = result.replace("<th>dateOfDeath</th>", "<th>dateOfDeath :" + criminal.getValues().get(7) + "</th>");
            result = result.replace("<th>numberOfCrimes</th>", "<th>numberOfCrimes :" + criminal.getValues().get(8) + "</th>");
        } else {
            result = "INVALID URL : " + path;
        }
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();

        if (fileBytes != null) {
            os.write(result.getBytes());
        }
        os.close();

    }
}
