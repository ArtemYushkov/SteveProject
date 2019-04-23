package com.javacore.yushkovartem.command;

import com.javacore.yushkovartem.Application;

public class CommandVersion extends ACommand {

    public CommandVersion(String name) {
        super(name);
    }

    public void execute() {
        System.out.println("My version is " + Application.VERSION);
    }
}
