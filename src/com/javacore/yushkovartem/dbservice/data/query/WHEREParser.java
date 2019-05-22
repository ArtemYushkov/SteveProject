package com.javacore.yushkovartem.dbservice.data.query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WHEREParser {

    private static final String WHERE_STRING = "^[\\s]*(WHERE)([\\s]*[a-zA-Z0-9><= ]+)$";

    public boolean validate(String whereString){

        return whereString.matches(WHERE_STRING);
    }

    public String beautify(String whereString) {
        return whereString.trim().replaceAll("[\\s]+", " ");
    }

    public String extractClause(String whereString) {
        Pattern p = Pattern.compile(WHERE_STRING);
        Matcher m = p.matcher(whereString);
        if (m.find()){
            return m.group(2);
        }
        return null;
    }

}
