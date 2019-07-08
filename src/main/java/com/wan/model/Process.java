package com.wan.model;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/11/7.
 */
public class Process {
    private int PROCESS_ID;
    private String TYPE;
    private int BUSINESS_ID;
    private String STATUS;
    private String OPINIONS;
    private int OPERATOR;
    private Timestamp CREATE_TIME;

    public Process() {
    }

    public Process(int PROCESS_ID, String TYPE, int BUSINESS_ID, String STATUS, String OPINIONS, int OPERATOR, Timestamp CREATE_TIME) {
        this.PROCESS_ID = PROCESS_ID;
        this.TYPE = TYPE;
        this.BUSINESS_ID = BUSINESS_ID;
        this.STATUS = STATUS;
        this.OPINIONS = OPINIONS;
        this.OPERATOR = OPERATOR;
        this.CREATE_TIME = CREATE_TIME;
    }

    public int getPROCESS_ID() {
        return PROCESS_ID;
    }

    public void setPROCESS_ID(int PROCESS_ID) {
        this.PROCESS_ID = PROCESS_ID;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public int getBUSINESS_ID() {
        return BUSINESS_ID;
    }

    public void setBUSINESS_ID(int BUSINESS_ID) {
        this.BUSINESS_ID = BUSINESS_ID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getOPINIONS() {
        return OPINIONS;
    }

    public void setOPINIONS(String OPINIONS) {
        this.OPINIONS = OPINIONS;
    }

    public int getOPERATOR() {
        return OPERATOR;
    }

    public void setOPERATOR(int OPERATOR) {
        this.OPERATOR = OPERATOR;
    }

    public Timestamp getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Timestamp CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    @Override
    public String toString() {
        return "Process{" +
                "PROCESS_ID=" + PROCESS_ID +
                ", TYPE='" + TYPE + '\'' +
                ", BUSINESS_ID=" + BUSINESS_ID +
                ", STATUS='" + STATUS + '\'' +
                ", OPINIONS='" + OPINIONS + '\'' +
                ", OPERATOR=" + OPERATOR +
                ", CREATE_TIME=" + CREATE_TIME +
                '}';
    }
}
