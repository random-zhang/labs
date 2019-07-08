package com.wan.model;

import java.util.ArrayList;
import java.util.List;


public class PageBean<T> {
	
	//当前页
	private int currentPage;
	//当前页显示的条数
	private int currentCount;
	//总条数
	private int totalCount;
	//总页数
	private int totalPage;
	//每页显示的数据
	private List<T> customerList = new ArrayList<T>();
	private List<T> LinkManList = new ArrayList<T>();
	private List<T> businessList = new ArrayList<T>();
	private List<T> businessDtoList = new ArrayList<T>();
	private List<T> customerDtoList = new ArrayList<T>();
 
	public List<T> getCustomerDtoList() {
		return customerDtoList;
	}

	public void setCustomerDtoList(List<T> customerDtoList) {
		this.customerDtoList = customerDtoList;
	}

	public List<T> getBusinessDtoList() {
		return businessDtoList;
	}

	public void setBusinessDtoList(List<T> businessDtoList) {
		this.businessDtoList = businessDtoList;
	}

	public List<T> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<T> businessList) {
		this.businessList = businessList;
	}

	public List<T> getLinkManList() {
		return LinkManList;
	}
	public void setLinkManList(List<T> linkManList) {
		LinkManList = linkManList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<T> customerList) {
		this.customerList = customerList;
	}

	
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageBean(int currentCount, int totalCount) {
		if (totalCount % currentCount != 0) {
			this.totalPage = totalCount / currentCount + 1;
		} else {
			this.totalPage = totalCount / currentCount;
		}
	}
	
	
}
