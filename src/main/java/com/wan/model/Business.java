package com.wan.model;

import java.sql.Timestamp;

public class Business {

	private int BUSINESS_ID;
	private int CUSTOMER_ID;
	private int LINK_MAIN_ID;
	private String BUSINESS_NAME;
	private int BUSINESS_TYPE_ID;
	private String STATUS;
	private int BUSINESS_SOURCE_ID;
	private float PERSALE_PRICE;
	// 赢单率
	private float PROBABILITY;
	private Timestamp VISIT_TIME;
	private String VISIT_CONTENT;
	private Timestamp UPDATE_TIME;
	private int EMPLOYEE_ID;
	public int getBUSINESS_ID() {
		return BUSINESS_ID;
	}
	public void setBUSINESS_ID(int bUSINESS_ID) {
		BUSINESS_ID = bUSINESS_ID;
	}
	public int getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}
	public void setCUSTOMER_ID(int cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}
	public int getLINK_MAIN_ID() {
		return LINK_MAIN_ID;
	}
	public void setLINK_MAIN_ID(int lINK_MAIN_ID) {
		LINK_MAIN_ID = lINK_MAIN_ID;
	}
	public String getBUSINESS_NAME() {
		return BUSINESS_NAME;
	}
	public void setBUSINESS_NAME(String bUSINESS_NAME) {
		BUSINESS_NAME = bUSINESS_NAME;
	}
	public int getBUSINESS_TYPE_ID() {
		return BUSINESS_TYPE_ID;
	}
	public void setBUSINESS_TYPE_ID(int bUSINESS_TYPE_ID) {
		BUSINESS_TYPE_ID = bUSINESS_TYPE_ID;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public int getBUSINESS_SOURCE_ID() {
		return BUSINESS_SOURCE_ID;
	}
	public void setBUSINESS_SOURCE_ID(int bUSINESS_SOURCE_ID) {
		BUSINESS_SOURCE_ID = bUSINESS_SOURCE_ID;
	}
	public float getPERSALE_PRICE() {
		return PERSALE_PRICE;
	}
	public void setPERSALE_PRICE(float pERSALE_PRICE) {
		PERSALE_PRICE = pERSALE_PRICE;
	}
	public float getPROBABILITY() {
		return PROBABILITY;
	}
	public void setPROBABILITY(float pROBABILITY) {
		PROBABILITY = pROBABILITY;
	}
	public Timestamp getVISIT_TIME() {
		return VISIT_TIME;
	}
	public void setVISIT_TIME(Timestamp vISIT_TIME) {
		VISIT_TIME = vISIT_TIME;
	}
	public String getVISIT_CONTENT() {
		return VISIT_CONTENT;
	}
	public void setVISIT_CONTENT(String vISIT_CONTENT) {
		VISIT_CONTENT = vISIT_CONTENT;
	}
	public Timestamp getUPDATE_TIME() {
		return UPDATE_TIME;
	}
	public void setUPDATE_TIME(Timestamp uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}
	public int getEMPLOYEE_ID() {
		return EMPLOYEE_ID;
	}
	public void setEMPLOYEE_ID(int eMPLOYEE_ID) {
		EMPLOYEE_ID = eMPLOYEE_ID;
	}
	public Business() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Business(int bUSINESS_ID, int cUSTOMER_ID, int lINK_MAIN_ID,
			String bUSINESS_NAME, int bUSINESS_TYPE_ID, String sTATUS,
			int bUSINESS_SOURCE_ID, float pERSALE_PRICE, float pROBABILITY,
			Timestamp vISIT_TIME, String vISIT_CONTENT, Timestamp uPDATE_TIME,
			int eMPLOYEE_ID) {
		super();
		BUSINESS_ID = bUSINESS_ID;
		CUSTOMER_ID = cUSTOMER_ID;
		LINK_MAIN_ID = lINK_MAIN_ID;
		BUSINESS_NAME = bUSINESS_NAME;
		BUSINESS_TYPE_ID = bUSINESS_TYPE_ID;
		STATUS = sTATUS;
		BUSINESS_SOURCE_ID = bUSINESS_SOURCE_ID;
		PERSALE_PRICE = pERSALE_PRICE;
		PROBABILITY = pROBABILITY;
		VISIT_TIME = vISIT_TIME;
		VISIT_CONTENT = vISIT_CONTENT;
		UPDATE_TIME = uPDATE_TIME;
		EMPLOYEE_ID = eMPLOYEE_ID;
	}

}
