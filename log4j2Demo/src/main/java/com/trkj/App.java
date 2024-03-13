package com.trkj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    private static Logger logger = LogManager.getLogger(App.class);


    public static void main( String[] args )
    {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入手机号:");
        String phone = input.next();
        logger.info("用户输入的手机号：{}",phone);
        while(!inputPhone(phone)){
            System.out.print("请输入手机号:");
            phone = input.next();
            logger.info("用户输入的手机号：{}",phone);
        }
        String subPhone = phone.substring(5);
        logger.debug("截取后手机号:{}",subPhone);
        subPhone = "6"+subPhone;
        logger.debug("短号:{}",subPhone);
    }

    public static boolean inputPhone(String phone){
        if(phone.length()!=11){
            logger.warn("手机号长度不是11位");
            return false;
        }
        if(!phone.matches("\\d{11}")){
            logger.error("手机号包含有非数字");
            return false;
        }
        return true;
    }
}
