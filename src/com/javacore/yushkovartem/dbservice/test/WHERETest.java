package com.javacore.yushkovartem.dbservice.test;

import com.javacore.yushkovartem.dbservice.data.query.WHEREParser;

public class WHERETest {

    public static final String VALID_STRING_1 = "WHERE id = 5";
    public static final String VALID_STRING_2 = "   WHERE id = 1 AND name= Tony   ";
    public static final String INVALID_STRING_2 = "WHERE id = 1 AND";
    public static final String INVALID_STRING_1 = "BAD WHERE";

    static WHEREParser parser = new WHEREParser();

    @Test
    public static void testValidString(){
        assert parser.validate(VALID_STRING_1) : VALID_STRING_1;
    }

    @Test
    public static void testInvalidString(){
        assert !parser.validate(INVALID_STRING_1) : INVALID_STRING_1;
    }

    @Test
    public static void testBeautifyValid() {
        assert parser.beautify(VALID_STRING_2).equals("WHERE id = 1 AND name= Tony");
    }

    @Test
    public static void testExtractClause() {
        assert parser.extractClause(VALID_STRING_1).equals(" id = 5");
        assert parser.extractClause(VALID_STRING_2).equals(" id = 1 AND name= Tony   ");
    }


    public static void notTest(){
        System.out.println("NOT A TEST METHOD");
    }

}
