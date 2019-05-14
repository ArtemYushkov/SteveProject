package com.javacore.yushkovartem.webservice;

import com.javacore.yushkovartem.dbservice.data.TableRow;
import com.javacore.yushkovartem.dbservice.dbstate.DBStateInit;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowCriminalByIdHandler implements HttpHandler {

    public static final String HTML_FORMAT = "^/api/show[cC]riminal[bB]y[Ii]d/[0-9]+$";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        int id;
        TableRow criminal;


        if (path.matches(HTML_FORMAT)){
            String idString = path.split("/")[3];
            System.out.println(idString);
            id = Integer.parseInt(idString);
            criminal = DBStateInit.getTable().getRow(id);

            byte[] fileBytes = Utils.readBytes("webclient/pages/criminals.html");
            String result = new String(fileBytes);

            result = result.replace("<th>ID</th>", "<th>ID : " + id + "</th>");
            result = result.replace("<th>firstName</th>", "<th>firstName :" + criminal.getValues().get(1) + "</th>");
            result = result.replace("<th>lastName</th>", "<th>lastName :" + criminal.getValues().get(2) + "</th>");
            result = result.replace("<th>nickname</th>", "<th>nickname :" + criminal.getValues().get(3) + "</th>");
            result = result.replace("<th>criminalFamilyId</th>", "<th>criminalFamilyId :" + criminal.getValues().get(4) + "</th>");
            result = result.replace("<th>dateOfBirth</th>", "<th>dateOfBirth :" + criminal.getValues().get(5) + "</th>");
            result = result.replace("<th>deceased</th>", "<th>deceased :" + criminal.getValues().get(6) + "</th>");
            result = result.replace("<th>dateOfDeath</th>", "<th>dateOfDeath :" + criminal.getValues().get(7) + "</th>");
            result = result.replace("<th>numberOfCrimes</th>", "<th>numberOfCrimes :" + criminal.getValues().get(8) + "</th>");

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();

            os.write(result.getBytes());
            os.close();
        }
    }
}
