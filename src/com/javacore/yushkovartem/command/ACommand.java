package com.javacore.yushkovartem.command;

public abstract class ACommand {
    protected String name;
    protected String description;

    public ACommand(String name){
        this.name = name;
    }

    public void execute(){
        System.out.println("Command \"" + name + "\" not implemented");
    }
}
