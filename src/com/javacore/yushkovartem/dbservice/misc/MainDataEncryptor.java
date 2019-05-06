package com.javacore.yushkovartem.dbservice.misc;

import com.javacore.yushkovartem.Application;

public class MainDataEncryptor implements DataEncryptor {
    @Override
    public String encrypt(String text) {
        if (Application.DATA_ENCRYPTION_LEVEL.equals("LOW")){
            return encryptLOW(text);
        }
        return null;
    }

    private String encryptLOW(String text) {
        //encrypt to HEX;
        StringBuilder result = new StringBuilder();
        for(char a : text.toCharArray()){
            result.append(Integer.toHexString(a));
        }
        return result.toString();
    }


    private String encryptMED(String text){
        //to caesar
        int k = 5;
        String lower_case = "abcdefghijklmnopqrstuvwxyz";
        String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder result = new StringBuilder();

        for(char a : text.toCharArray()){
            String sA = String.valueOf(a);
            if (lower_case.contains(sA)){
                char encryptedLetter = getEncryptedLetter(sA,k,lower_case);// lower_case.charAt(posOfLetter + k);
                result.append(encryptedLetter);
            } else if (upper_case.contains(sA)){
                char encryptedLetter = getEncryptedLetter(sA,k,upper_case);
                result.append(encryptedLetter);
            } else {
                result.append(sA);
            }
        }
        return result.toString();
    }

    private static char getEncryptedLetter(String s, int k, String alphabet) {
        char encryptedLetter;
        int posOfLetter = alphabet.indexOf(s);
        encryptedLetter = alphabet.charAt((posOfLetter + k)%26);
        return encryptedLetter;
    }

    private String encryptHIGH(String text){

        return null;
    }
}
