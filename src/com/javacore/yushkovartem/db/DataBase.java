package com.javacore.yushkovartem.db;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataBase {

    public static final String[] CRIMNINALS_COLUMNS = new String[] {
            "id", "firstName" //field from model
    };

    public static final String[] CRIME_FAMILY_COLUMNS = new String[] {
            "id", "name"
    };

    Map<String, Table> tables;

    //DB.querry("SELECT ID, NAME, EMAIL, FROM CRIMINALS");

    Object tableLock = new Object();

    public List<Record> select(){
        System.out.println("Starting fetching records...");
        synchronized (tableLock) {
            try {
                Thread.sleep(500);
                System.out.println("Finished fetching records...");
            } catch (InterruptedException e) {

            }
        }

        return null;
    }

    public void update(){
        System.out.println("Starting updating database...");
        synchronized (tableLock) {

            try {
                Thread.sleep(10000);
                System.out.println("Finished updating database...");
            } catch (InterruptedException e) {

            }
        }
    }

    public void init() {
        // load data to table
    }

    public static List<String[]> readDataFile(String fileName) {
        List<String[]> result = new ArrayList<>();
        FileInputStream fis = null; // побайтовый поток для чтения файла
        BufferedReader br = null; //
        try {
            fis = new FileInputStream(fileName);
            br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                result.add(line.split(";"));
            }
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        } catch (IOException io){

        } finally {
            try {
                fis.close();
                br.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        return result;
    }

    public void delete(){

    }

    public void insert(Record record, Table table){

    }
}
