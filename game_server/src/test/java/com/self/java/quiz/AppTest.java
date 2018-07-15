package com.self.java.quiz;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testUser(){
        try {
            new HttpClient().connect("127.0.0.1",9090);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Rigourous Test :-)
     */
    /*
    public void testAuth() {
//        for(int i =0;i<10;i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
        System.out.println("testAuth");
        HttpClient client = new HttpClient();
        try {

            client.connect("ws://quiz.jktom.com", 9001);
        } catch (Exception e) {
            e.printStackTrace();
        }
//                }
//            }).start();
//        }

    }

    public void testDB(){
        try {
            TestDB.testLoad();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

}
