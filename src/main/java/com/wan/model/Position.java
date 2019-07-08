package com.wan.model;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/11/7.
 */
public class Position {
    private int POSITION_ID;
    private String POSITION_NAME;
    private String POSITION_LEVEL;
    private Timestamp CREATE_TIME;
    private Timestamp UPDATE_TIME;

    public Position() {
    }

    public Position(int POSITION_ID, String POSITION_NAME, String POSITION_LEVEL, Timestamp CREATE_TIME, Timestamp UPDATE_TIME) {
        this.POSITION_ID = POSITION_ID;
        this.POSITION_NAME = POSITION_NAME;
        this.POSITION_LEVEL = POSITION_LEVEL;
        this.CREATE_TIME = CREATE_TIME;
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public int getPOSITION_ID() {
        return POSITION_ID;
    }

    public void setPOSITION_ID(int POSITION_ID) {
        this.POSITION_ID = POSITION_ID;
    }

    public String getPOSITION_NAME() {
        return POSITION_NAME;
    }

    public void setPOSITION_NAME(String POSITION_NAME) {
        this.POSITION_NAME = POSITION_NAME;
    }

    public String getPOSITION_LEVEL() {
        return POSITION_LEVEL;
    }

    public void setPOSITION_LEVEL(String POSITION_LEVEL) {
        this.POSITION_LEVEL = POSITION_LEVEL;
    }

    public Timestamp getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Timestamp CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public Timestamp getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(Timestamp UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    @Override
    public String toString() {
        return "Position{" +
                "POSITION_ID=" + POSITION_ID +
                ", POSITION_NAME='" + POSITION_NAME + '\'' +
                ", POSITION_LEVEL='" + POSITION_LEVEL + '\'' +
                ", CREATE_TIME=" + CREATE_TIME +
                ", UPDATE_TIME=" + UPDATE_TIME +
                '}';
    }
}
