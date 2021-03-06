package com.javacore.yushkovartem.dbservice.dbstate;

import com.javacore.yushkovartem.dbservice.DBApplication;

public class DBStateRunning extends DBState {

    public DBStateRunning(String name){
        super(name);
    }

    @Override
    public void enter() {
        System.out.println("Entering DBRunning state");
    }

    @Override
    public void onStop() {
        //check if everything is ok
        DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateStop);
    }


}
