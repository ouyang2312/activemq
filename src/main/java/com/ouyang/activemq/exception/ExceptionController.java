package com.ouyang.activemq.exception;

import com.ouyang.activemq.entity.User;
import com.ouyang.activemq.test.CommonEmailTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author oy
 * @description
 * @date 2019/11/6
 */
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public User runtimeException(RuntimeException e) {
        e.printStackTrace();
        StackTraceElement[] stackTrace = e.getStackTrace();
        String str = "";
        for (int i = 0; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            str += "</br>";
            str += stackTraceElement.toString();
        }
        //发送邮件
        try {
            CommonEmailTest.sendEmail(str);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        User user = new User();
        user.setId(1L);
        user.setName("ouyang");
        return user;
    }

}
