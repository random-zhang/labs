package com.wan.model;

/**
 * Created by Administrator on 2017/11/7.
 */
public class BusinessType {
    private int TYPE_ID;
    private String TYPE_NAME;

    public BusinessType() {
    }

    public BusinessType(int TYPE_ID, String TYPE_NAME) {
        this.TYPE_ID = TYPE_ID;
        this.TYPE_NAME = TYPE_NAME;
    }

    public int getTYPE_ID() {
        return TYPE_ID;
    }

    public void setTYPE_ID(int TYPE_ID) {
        this.TYPE_ID = TYPE_ID;
    }

    public String getTYPE_NAME() {
        return TYPE_NAME;
    }

    public void setTYPE_NAME(String TYPE_NAME) {
        this.TYPE_NAME = TYPE_NAME;
    }

    @Override
    public String toString() {
        return "BusinessType{" +
                "TYPE_ID=" + TYPE_ID +
                ", TYPE_NAME='" + TYPE_NAME + '\'' +
                '}';
    }
}
