package com.javacore.yushkovartem.dbservice.dbstate;

import com.javacore.yushkovartem.dbservice.DBApplication;
import com.javacore.yushkovartem.dbservice.misc.DBConstants;
import com.javacore.yushkovartem.dbservice.misc.Utils;
import com.javacore.yushkovartem.dbservice.misc.DataHandler;
import com.javacore.yushkovartem.dbservice.data.Table;
import com.javacore.yushkovartem.dbservice.data.TableMetaData;
import com.javacore.yushkovartem.dbservice.server.DBServer;

public class DBStateInit extends DBState {

    // <начало>КОСТЫЛИЩЕ!
    public static Table table;

    public static Table getTable() {
        return table;
    }
    //<конец> КОСТЫЛИЩЕ


    public DBStateInit(String name) {
        super(name);
    }



    @Override
    public void enter() {
        System.out.println("Entering DBInit state");
        initTables();
        try{
            DBServer.INSTANCE.start();
            DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateRun);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initTables() {
        Utils.readDir(DBConstants.TABLE_DIR, new DataHandler() {
            @Override
            public void handleFile(String filePath) {
                TableMetaData metaData = TableMetaData.loadFromFile(filePath);
                //<начало>КОСТЫЛИЩЕ продолжение
                table = new Table(metaData);
                //<конец> КОСТЫЛИЩЕ продолжение

                //Table table = new Table(metaData); //раскомментить. Для КОСТЫЛИЩА.
                table.load();
            }
        });
    }

    @Override
    public void onStop() {
        //check if everything is ok
        DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateStop);
    }
}
