package com.javacore.yushkovartem.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CommandRegistry {
    INSTANCE;

    static Map<String, ACommand> commands;

    static {
        commands = new HashMap<>();
        CommandAuthor commandAuthor = new CommandAuthor("author");
        CommandAppName commandAppName = new CommandAppName("appname");
        commands.put("version", new CommandVersion("version"));
        commands.put("author", commandAuthor);
        commands.put("creator", commandAuthor);
        commands.put("father", commandAuthor);
        commands.put("name", commandAppName);
        commands.put("appname", commandAppName);
    }


    public boolean hasCommand(String name) {
        return commands.containsKey(name);
    }

    public ACommand getCommand(String name){
        return commands.get(name);
    }

    public void listCommands(){
        Iterator it = commands.entrySet().iterator();
        System.out.println("List of commands");
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey());
        }
    }
}
