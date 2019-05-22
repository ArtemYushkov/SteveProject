package com.javacore.yushkovartem.dbservice;

import com.javacore.yushkovartem.dbservice.data.Table;
import com.javacore.yushkovartem.dbservice.data.query.QueryResult;
import com.javacore.yushkovartem.dbservice.dbstate.DBState;
import com.javacore.yushkovartem.dbservice.dbstate.DBStateInit;
import com.javacore.yushkovartem.dbservice.dbstate.DBStateRunning;
import com.javacore.yushkovartem.dbservice.dbstate.DBStateStop;
import com.javacore.yushkovartem.dbservice.test.Test;

import java.lang.reflect.Method;
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

        boolean testEnabled = Boolean.valueOf(System.getProperty("et"));
        if(testEnabled) {
            try{
                runTests("com.javacore.yushkovartem.dbservice.test.WHERETest");
            } catch(Exception e){
                e.printStackTrace();
            }
        }

       // changeState(stateInit);
    }

    public void stop() {
        currentState.onStop();
    }

    public QueryResult query(String query) {
        return currentState.onQuery(query);
    }

    public String getStateName() {
        return currentState.getName();
    }

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

    public void addTable(String tableName, Table table) {
        tables.put(tableName, table);
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    private void runTests(String className) throws Exception {
        int passed = 0;
        int failed = 0;

        for (Method m : Class.forName(className).getMethods()){
            Test test = m.getAnnotation(Test.class);
            if(test != null && test.enabled()) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("WHERETest %s failed: %s \n", m, ex.getCause());
                    failed++;
                }
            }
        }

        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }

}
