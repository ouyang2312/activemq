package com.ouyang.activemq.test;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author oy
 * @description
 * @date 2019/11/5
 */
public class ReflectTest {

    private static Map<String, Method> map = new HashMap<>();

    public static void main(String[] args) throws Exception{
        //初始化map
        Class<methodTest> methodTestClass = methodTest.class;
//        Method[] methods = methodTestClass.getMethods();
        Method[] methods = methodTestClass.getDeclaredMethods();
        if(methods != null && methods.length > 0){
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                String name = method.getName();
                map.put(name,method);
            }
        }
        //模拟传值过程
        String req = "test3";
        Method method = map.get(req);
//        methodTest methodTest = new methodTest();
        Object invoke = method.invoke(null, null);
    }

}

class methodTest{
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
