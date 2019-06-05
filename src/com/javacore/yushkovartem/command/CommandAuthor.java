package com.javacore.yushkovartem.command;

import com.javacore.yushkovartem.MainApplication;

public class CommandAuthor extends ACommand {

    public CommandAuthor(String name) {
        super(name);
    }

    public void execute(){
        System.out.println("My author is " + MainApplication.AUTHOR);
    }
}
