package com.javacore.yushkovartem.command;

import com.javacore.yushkovartem.Application;

public class CommandAppName extends ACommand {
    public CommandAppName(String name) {
        super(name);
    }

    public void execute(){
        System.out.println("My AppName is " + Application.APP_NAME);
    }
}
