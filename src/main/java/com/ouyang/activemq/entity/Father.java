package com.ouyang.activemq.entity;

/**
 * @author oy
 * @description
 * @date 2019/10/22
 */
public class Father {

    private String aaa;

    public Father() {
    }

    public Father(String aaa) {
        this.aaa = aaa;
    }

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    @Override
    public String toString() {
        return "Father{" +
                "aaa='" + aaa + '\'' +
                '}';
    }
}
