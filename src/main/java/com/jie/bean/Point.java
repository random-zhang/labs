package com.jie.bean;

import java.io.Serializable;

public  class Point implements Serializable {
    double cv;
    double ct;

    public Point(double ct,double cv){
        this.ct=ct;
        this.cv=cv;
    }
    public Point(){
    }
    public double getCv() {
        return cv;
    }

    public void setCv(double cv) {
        this.cv = cv;
    }

    public double getCt() {
        return ct;
    }

    public void setCt(double ct) {
        this.ct = ct;
    }

    public Point(String coordinate){//n,n
        String[] s=coordinate.split(",");
        this.ct=Double.parseDouble(s[0]);
        this.cv=Double.parseDouble(s[1]);
    }
    /*public String toString(){
        return "("+ct+","+cv+")";
    }*/
}