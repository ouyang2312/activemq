package com.ouyang.activemq.controller;

import com.ouyang.activemq.dao.UserDao;
import com.ouyang.activemq.entity.User;
import com.ouyang.activemq.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author oy
 * @description
 * @date 2019/11/5
 */
@RequestMapping(value = "/reflect")
@RestController
public class ReflectController {

    @Autowired
    UserDao userDao;
    @Autowired
    UserDaoService userDaoService;

    @RequestMapping(value = "/test")
    public User test(){
        User user = userDao.selectById(1);
        return user;
    }

    @GetMapping(value = "bug")
    public void bug(){
        int a = 0;
        int b = 10;
        System.out.println(b/a);
        System.out.println("测试业务流程");
    }

}
