package com.wan.model;

import java.sql.Timestamp;

public class Customer {

	private int CUSTOMER_ID;
    private String CUSTOMER_NAME;
    private String POST_CODE;
    private int FIELD_ID;
    private int SOURCE_ID;
    private Timestamp CREATE_TIME;
    private Timestamp UPDATE_TIME;
    private String EMPLOYEE_NUMBERS;
    private int PRINPICAL;
    private String ADDRESS;
    private String TAG;
    private String REMARKS;
    private String BUSUBESS_VOLUME;
	public int getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}
	public void setCUSTOMER_ID(int cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}
	public String getCUSTOMER_NAME() {
		return CUSTOMER_NAME;
	}
	public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
		CUSTOMER_NAME = cUSTOMER_NAME;
	}
	public String getPOST_CODE() {
		return POST_CODE;
	}
	public void setPOST_CODE(String pOST_CODE) {
		POST_CODE = pOST_CODE;
	}
	public int getFIELD_ID() {
		return FIELD_ID;
	}
	public void setFIELD_ID(int fIELD_ID) {
		FIELD_ID = fIELD_ID;
	}
	public int getSOURCE_ID() {
		return SOURCE_ID;
	}
	public void setSOURCE_ID(int sOURCE_ID) {
		SOURCE_ID = sOURCE_ID;
	}
	public Timestamp getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(Timestamp cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public Timestamp getUPDATE_TIME() {
		return UPDATE_TIME;
	}
	public void setUPDATE_TIME(Timestamp uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}
	public String getEMPLOYEE_NUMBERS() {
		return EMPLOYEE_NUMBERS;
	}
	public void setEMPLOYEE_NUMBERS(String eMPLOYEE_NUMBERS) {
		EMPLOYEE_NUMBERS = eMPLOYEE_NUMBERS;
	}
	public int getPRINPICAL() {
		return PRINPICAL;
	}
	public void setPRINPICAL(int pRINPICAL) {
		PRINPICAL = pRINPICAL;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getTAG() {
		return TAG;
	}
	public void setTAG(String tAG) {
		TAG = tAG;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public String getBUSUBESS_VOLUME() {
		return BUSUBESS_VOLUME;
	}
	public void setBUSUBESS_VOLUME(String bUSUBESS_VOLUME) {
		BUSUBESS_VOLUME = bUSUBESS_VOLUME;
	}
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int cUSTOMER_ID, String cUSTOMER_NAME, String pOST_CODE,
			int fIELD_ID, int sOURCE_ID, Timestamp cREATE_TIME,
			Timestamp uPDATE_TIME, String eMPLOYEE_NUMBERS, int pRINPICAL,
			String aDDRESS, String tAG, String rEMARKS, String bUSUBESS_VOLUME) {
		super();
		CUSTOMER_ID = cUSTOMER_ID;
		CUSTOMER_NAME = cUSTOMER_NAME;
		POST_CODE = pOST_CODE;
		FIELD_ID = fIELD_ID;
		SOURCE_ID = sOURCE_ID;
		CREATE_TIME = cREATE_TIME;
		UPDATE_TIME = uPDATE_TIME;
		EMPLOYEE_NUMBERS = eMPLOYEE_NUMBERS;
		PRINPICAL = pRINPICAL;
		ADDRESS = aDDRESS;
		TAG = tAG;
		REMARKS = rEMARKS;
		BUSUBESS_VOLUME = bUSUBESS_VOLUME;
	}
	
    
}
