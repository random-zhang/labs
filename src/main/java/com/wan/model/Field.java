package com.wan.model;

/**
 * ������ҵ
 * Created by Administrator on 2017/11/6.
 */
public class Field {
    private int FIELD_ID;
    private String FIELD_NAME;

    public Field() {
    }

    public Field(int FIELD_ID, String FIELD_NAME) {
        this.FIELD_ID = FIELD_ID;
        this.FIELD_NAME = FIELD_NAME;
    }

    public int getFIELD_ID() {
        return FIELD_ID;
    }

    public void setFIELD_ID(int FIELD_ID) {
        this.FIELD_ID = FIELD_ID;
    }

    public String getFIELD_NAME() {
        return FIELD_NAME;
    }

    public void setFIELD_NAME(String FIELD_NAME) {
        this.FIELD_NAME = FIELD_NAME;
    }

    @Override
    public String toString() {
        return "Field{" +
                "FIELD_ID=" + FIELD_ID +
                ", FIELD_NAME='" + FIELD_NAME + '\'' +
                '}';
    }
}
