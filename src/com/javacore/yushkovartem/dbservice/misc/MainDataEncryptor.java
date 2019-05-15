package com.javacore.yushkovartem.dbservice.misc;

import com.javacore.yushkovartem.Application;
import com.javacore.yushkovartem.dbservice.DBApplication;

public class MainDataEncryptor implements DataEncryptor {
    @Override
    public String encrypt(String text) {
        if (DBApplication.DATA_ENCRYPTION_LEVEL.equals("LOW")){
            return encryptLOW(text);
        }
        return null;
    }

    private String encryptLOW(String text) {
        //to hex
        return null;
    }

    private String encryptMED(String text) {
        //to caesar
        return null;
    }

    private String encryptHIGH(String text) {
        return null;
    }

}
