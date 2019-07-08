package com.wan.model;

/**
 * Created by Administrator on 2017/11/6.
 */
public class Source {
    private int SOURCE_ID;
    private String SOURCE_NAME;

    public Source() {
    }

    public Source(int SOURCE_ID, String SOURCE_NAME) {
        this.SOURCE_ID = SOURCE_ID;
        this.SOURCE_NAME = SOURCE_NAME;
    }

    public int getSOURCE_ID() {
        return SOURCE_ID;
    }

    public void setSOURCE_ID(int SOURCE_ID) {
        this.SOURCE_ID = SOURCE_ID;
    }

    public String getSOURCE_NAME() {
        return SOURCE_NAME;
    }

    public void setSOURCE_NAME(String SOURCE_NAME) {
        this.SOURCE_NAME = SOURCE_NAME;
    }

    @Override
    public String toString() {
        return "Source{" +
                "SOURCE_ID=" + SOURCE_ID +
                ", SOURCE_NAME='" + SOURCE_NAME + '\'' +
                '}';
    }
}
