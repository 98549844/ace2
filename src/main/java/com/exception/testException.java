package com.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;
/**
 * @Classname: testException
 * @Date: 10/12/2021 4:39 AM
 * @Author: garlam
 * @Description:
 */


public class testException extends Exception {
    private static final Logger log = LogManager.getLogger(testException.class.getName());

    public testException() {
        super();
        System.err.println("ACE EXCEPTION");
    }

    public testException(String message) {
        super(message);
        System.err.println("ACE EXCEPTION: " + message);

    }


    static Scanner sc = null;

    static int check(String c) throws  testException {
        if (!c.matches("^\\-?[0-9]+$")) {  //匹配所有的整数
            throw new testException("testException : 请输入整数数字！, " + c);

        }
        if ((Integer.valueOf(c)) > 100 || (Integer.valueOf(c)) < 0) {
            throw new testException("testException: 请输入0到100之间的整数！, " + c);
        }
        return Integer.valueOf(c);

    }


    public static void main(String[] args) {
        System.out.println("请输出一个整数：");
        String c = "666";
        //sc = new Scanner(System.in);
        //sc = new Scanner(System.in);
        try {
            int a = check(c);
            if (a <= 100 && a >= 90) {
                System.out.println("优！");
            } else if (a >= 80 && a < 90) {
                System.out.println("良！");
            } else if (a >= 70 && a < 80) {
                System.out.println("中！");
            } else if (a >= 60 && a < 70) {
                System.out.println("及格！");
            } else {
                System.out.println("不及格！");
            }
        } catch (testException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

