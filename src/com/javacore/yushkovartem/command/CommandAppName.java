package com.javacore.yushkovartem.command;

import com.javacore.yushkovartem.MainApplication;

public class CommandAppName extends ACommand {
    public CommandAppName(String name) {
        super(name);
    }

    public void execute(){
        System.out.println("My AppName is " + MainApplication.APP_NAME);
    }
}
