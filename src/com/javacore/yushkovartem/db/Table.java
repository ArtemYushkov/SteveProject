package com.javacore.yushkovartem.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Table {

    protected String name;
    protected List<String> columns;
    protected List<Record> records;

    {
        records = new ArrayList<Record>();
    }

    public Table(String name, List<String> columns){
        this.name = name;
        this.columns = columns;
    }


    public void insert(Record record){
        records.add(record);
    }

    public List<String> getColumns() {
        return columns;
    }

    //SELECT id, firstName, lastName;

    public void select(String query){
        ArrayList<String> request = new ArrayList<>(Arrays.asList(query.split(" ")));
        ArrayList<String> result = new ArrayList<>();
        StringBuilder firstLine = new StringBuilder();
        for(String s : request){
            firstLine.append(s).append("\t");
        }

        result.add(firstLine.toString());

        for(int i = 0; i < records.size(); i++){
            StringBuilder line = new StringBuilder();
            for(String s : request){
                line.append(selectField(s).get(i)).append("\t");
            }
            result.add(line.toString());
        }

        System.out.print("Loading");

        Runnable printLoading = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                    System.out.print(".");
                    Thread.sleep(100);
                    }
                    System.out.println();
                } catch (InterruptedException e){}
            }
        };

        printLoading.run();


        for(String a : result){
            System.out.println(a);
        }
    }

    //------------------------------
    // ID   firstname    lastname
    //--------------------------
    // 1    Vladimir    Trump

    public List<String> selectField(String fieldName){
        int index = columns.indexOf(fieldName);
        Iterator it = records.iterator();
        List<String> result = new ArrayList<>();

        while(it.hasNext()){
            Record r = (Record)it.next();
            result.add(r.values.get(index));
        }
        return result;
    }

}
