package com.ouyang.activemq.service;

import com.ouyang.activemq.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author oy
 * @description
 * @date 2019/11/5
 */
@Service
public class UserDaoService {

    @Autowired
    UserDao userDao;
    
    public static void test1(){
        System.out.println("test1");
    }

    public static void test2(){
        System.out.println("test2");
    }

    public static void test3(){
        System.out.println("test3");
    }
}
