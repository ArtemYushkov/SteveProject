package com.javacore.yushkovartem;

import com.javacore.yushkovartem.appserver.ServerApplication;
import com.javacore.yushkovartem.dbservice.DBApplication;
import com.javacore.yushkovartem.state.ApplicationState;
import com.javacore.yushkovartem.webservice.WebClientApplication;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Application {

    public static final String OP_GROUP = "^(SELECT|DELETE)";
    public static final String FLD_GROUP = "([a-zA-Z, ]+)";
    public static final String SPACE = "([\\s]+)";
    public static final String FROM_GROUP = "(FROM)";
    public static final String TBL_GROUP = "([a-zA-Z]+)$";

    public static final String DATA_ENCRYPTION_LEVEL = "LOW";



    public static final String ALLOWED_DOMAIN_NAMES = "^([a-zA-Z0-9]+\\.)+(com|de|ru)";

    static public final String APP_NAME = "Steve";
    static public final String AUTHOR = "Artem Yushkov";
    static public final String VERSION = "0.0.0";
    static ApplicationState currentState;

    public static void main(String[] args) {

        try {
            WebClientApplication.INSTANCE.start();
            ServerApplication.INSTANCE.start();
            DBApplication.INSTANCE.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        ConsoleCanvas canvas = new ConsoleCanvas(50,50);
//        canvas.drawCircleAt(25,25,10);
//

//        String query = "SELECT id, firstName, lastName FROM Criminals";
//
//        Pattern p  = Pattern.compile(OP_GROUP + SPACE + FLD_GROUP + SPACE + FROM_GROUP + SPACE + TBL_GROUP);
//        Matcher matcher = p.matcher(query);
//        if (matcher.find()) {
//            System.out.println("Number of groups: " + matcher.groupCount());
//
//        }
    }

    public static void printList(List<String> list){
        for(String s : list){
            System.out.println(s);
        }
    }

    public static void printList(List<String> list, String filter){
        for(String s : list) {
            if (s.matches(filter)) {
                System.out.println(s);
            }
        }
    }

    public static void iteratorCleanup(List<String> list, String filter){
        Iterator<String> it = list.iterator();
        while(it.hasNext()) {
            String s = it.next();
            if (!s.matches(filter)){
                it.remove();
            }
        }
    }

    public static void foreachCleanup(List<String> list, String filter){
        for (String s : list){
            if (!s.matches(filter)){
                list.remove(s);
            }
        }
    }



    public static void changeState(ApplicationState newState, String commandName){
        if (currentState != null){
            currentState.exit();
        }
        currentState = newState;

        newState.enter(commandName);
    }

}
