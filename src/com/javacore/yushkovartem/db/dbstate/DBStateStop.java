package com.javacore.yushkovartem.db.dbstate;

import com.javacore.yushkovartem.db.misc.DBConstants;
import com.javacore.yushkovartem.db.misc.DataEncryptor;
import com.javacore.yushkovartem.db.misc.Utils;

import java.util.ArrayList;
import java.util.List;

public class DBStateStop extends DBState {

    public DBStateStop(String name){
        super(name);
    }

    @Override
    public void enter() {
        System.out.println("Entering DBStop state");

        List<String> list = new ArrayList<>();
        list.add("test string one");
        list.add("test string two");
        Utils.writeListToFile(list, DBConstants.DATA_DIR + "/test.dat", new DataEncryptor() {
            @Override
            public String encrypt(String text) {
                return null;
            }
        });
    }

    @Override
    public void onStop() {
        System.out.println("Already trying to stop...");
    }
}
