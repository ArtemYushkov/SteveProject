package com.javacore.yushkovartem.dbservice;

import com.javacore.yushkovartem.dbservice.data.QueryResult;
import com.javacore.yushkovartem.dbservice.data.Table;
import com.javacore.yushkovartem.dbservice.dbstate.DBState;
import com.javacore.yushkovartem.dbservice.dbstate.DBStateInit;
import com.javacore.yushkovartem.dbservice.dbstate.DBStateRunning;
import com.javacore.yushkovartem.dbservice.dbstate.DBStateStop;

import java.util.HashMap;
import java.util.Map;

public enum DBApplication {
    INSTANCE;


    private Map<String, Table> tables = new HashMap<>();

    public static final String DATA_ENCRYPTION_LEVEL = "LOW";
    private DBState currentState;
    public DBState stateInit = new DBStateInit("Initializing");
    public DBState stateRun = new DBStateRunning("Running");
    public DBState stateStop = new DBStateStop("Shutting Down");

    public void start() {
        changeState(stateInit);
    }

    public void stop() {
        currentState.onStop();
    }

    public QueryResult query(String query) {
        return null;
    }

    public String getStateName() {return currentState.getName();}

    public void changeState(DBState state) {
        if (currentState != null) {
            if (currentState.equals(state)) {
                return;
            } else {
                currentState.exit();
            }
        }
        currentState = state;
        currentState.enter();
    }

}
