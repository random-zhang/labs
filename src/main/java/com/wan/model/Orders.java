package com.wan.model;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/11/6.
 */
public class Orders {
    private int ORDER_ID;
    private String ORDER_SEQ;
    private String TITLE;
    private int CUSTOMER_ID;
    private float TOTAL_MONEY;
    private String STATUS;
    private int OPERATOR;
    private Timestamp ORDER_TIME;
    private Timestamp CREATE_TIME;
    private String ORDER_TYPE;

    public Orders() {
    }

    public Orders(int ORDER_ID, String ORDER_SEQ, String TITLE, int CUSTOMER_ID, float TOTAL_MONEY, String STATUS, int OPERATOR, Timestamp ORDER_TIME, Timestamp CREATE_TIME, String ORDER_TYPE) {
        this.ORDER_ID = ORDER_ID;
        this.ORDER_SEQ = ORDER_SEQ;
        this.TITLE = TITLE;
        this.CUSTOMER_ID = CUSTOMER_ID;
        this.TOTAL_MONEY = TOTAL_MONEY;
        this.STATUS = STATUS;
        this.OPERATOR = OPERATOR;
        this.ORDER_TIME = ORDER_TIME;
        this.CREATE_TIME = CREATE_TIME;
        this.ORDER_TYPE = ORDER_TYPE;
    }

    public int getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(int ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getORDER_SEQ() {
        return ORDER_SEQ;
    }

    public void setORDER_SEQ(String ORDER_SEQ) {
        this.ORDER_SEQ = ORDER_SEQ;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(int CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public float getTOTAL_MONEY() {
        return TOTAL_MONEY;
    }

    public void setTOTAL_MONEY(float TOTAL_MONEY) {
        this.TOTAL_MONEY = TOTAL_MONEY;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public int getOPERATOR() {
        return OPERATOR;
    }

    public void setOPERATOR(int OPERATOR) {
        this.OPERATOR = OPERATOR;
    }

    public Timestamp getORDER_TIME() {
        return ORDER_TIME;
    }

    public void setORDER_TIME(Timestamp ORDER_TIME) {
        this.ORDER_TIME = ORDER_TIME;
    }

    public Timestamp getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Timestamp CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getORDER_TYPE() {
        return ORDER_TYPE;
    }

    public void setORDER_TYPE(String ORDER_TYPE) {
        this.ORDER_TYPE = ORDER_TYPE;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ORDER_ID=" + ORDER_ID +
                ", ORDER_SEQ='" + ORDER_SEQ + '\'' +
                ", TITLE='" + TITLE + '\'' +
                ", CUSTOMER_ID=" + CUSTOMER_ID +
                ", TOTAL_MONEY=" + TOTAL_MONEY +
                ", STATUS='" + STATUS + '\'' +
                ", OPERATOR=" + OPERATOR +
                ", ORDER_TIME=" + ORDER_TIME +
                ", CREATE_TIME=" + CREATE_TIME +
                ", ORDER_TYPE='" + ORDER_TYPE + '\'' +
                '}';
    }
}
