package com.ouyang.activemq.entity;

import java.lang.reflect.Field;

/**
 * @author oy
 * @description
 * @date 2019/10/22
 */
public class Son extends Father {

    private String bbb;

    public Son() {
    }

    public String getBbb() {
        return bbb;
    }

    public void setBbb(String bbb) {
        this.bbb = bbb;
    }

    public Son(String aaa, String bbb) {
        super(aaa);
        this.bbb = bbb;
    }

    public static void main(String[] args)throws Exception{
        Son son = new Son();
        son.setBbb("bString");
        son.setAaa("aString");
        Class<? extends Son> sonClass = son.getClass();

//        Class<Son> sonClass = Son.class;
        Field[] fields = sonClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName() + ":" + field.get(son));
        }

        Class<?> superclass = sonClass.getSuperclass();
        Field[] declaredFields = superclass.getDeclaredFields();
        for (Field field : declaredFields) {
//            System.out.println(field.getName());
            field.setAccessible(true);
            System.out.println(field.getName() + ":" + field.get(son));
        }

    }

}
