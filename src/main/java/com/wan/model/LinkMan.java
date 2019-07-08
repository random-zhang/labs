package com.wan.model;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/11/6.
 */
public class LinkMan {
    private int LINK_MAIN_ID;
    private String NAME;
    private String SEX;
    private String POSITION;
    private String NICKNAME;
    private int PHONENUM;
    private String EMAIL;
    private int QQ;
    private Timestamp CREATE_TIME;
    private Timestamp UPDATE_TIME;
    private String REMARKS;
    private String QR_CODE;
    private int CUSTOMER_ID;

    public LinkMan() {
    }

    public LinkMan(int LINK_MAIN_ID, String NAME, String SEX, String POSITION, String NICKNAME, int PHONENUM, String EMAIL, int QQ, Timestamp CREATE_TIME, Timestamp UPDATE_TIME, String REMARKS, String QR_CODE, int CUSTOMER_ID) {
        this.LINK_MAIN_ID = LINK_MAIN_ID;
        this.NAME = NAME;
        this.SEX = SEX;
        this.POSITION = POSITION;
        this.NICKNAME = NICKNAME;
        this.PHONENUM = PHONENUM;
        this.EMAIL = EMAIL;
        this.QQ = QQ;
        this.CREATE_TIME = CREATE_TIME;
        this.UPDATE_TIME = UPDATE_TIME;
        this.REMARKS = REMARKS;
        this.QR_CODE = QR_CODE;
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public int getLINK_MAIN_ID() {
        return LINK_MAIN_ID;
    }

    public void setLINK_MAIN_ID(int LINK_MAIN_ID) {
        this.LINK_MAIN_ID = LINK_MAIN_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getPOSITION() {
        return POSITION;
    }

    public void setPOSITION(String POSITION) {
        this.POSITION = POSITION;
    }

    public String getNICKNAME() {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }

    public int getPHONENUM() {
        return PHONENUM;
    }

    public void setPHONENUM(int PHONENUM) {
        this.PHONENUM = PHONENUM;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public int getQQ() {
        return QQ;
    }

    public void setQQ(int QQ) {
        this.QQ = QQ;
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

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public String getQR_CODE() {
        return QR_CODE;
    }

    public void setQR_CODE(String QR_CODE) {
        this.QR_CODE = QR_CODE;
    }

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(int CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "LINK_MAIN_ID=" + LINK_MAIN_ID +
                ", NAME='" + NAME + '\'' +
                ", SEX='" + SEX + '\'' +
                ", POSITION='" + POSITION + '\'' +
                ", NICKNAME='" + NICKNAME + '\'' +
                ", PHONENUM=" + PHONENUM +
                ", EMAIL='" + EMAIL + '\'' +
                ", QQ=" + QQ +
                ", CREATE_TIME=" + CREATE_TIME +
                ", UPDATE_TIME=" + UPDATE_TIME +
                ", REMARKS='" + REMARKS + '\'' +
                ", QR_CODE='" + QR_CODE + '\'' +
                ", CUSTOMER_ID=" + CUSTOMER_ID +
                '}';
    }
}
