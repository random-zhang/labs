package com.wan.util;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
	private int pageNo ;    // 需要显示的页码
	private int totalPages ;   // 总页数
	private int pageSize ;    // 每页记录数
	private int totalRecords ; // 总记录数
	private boolean isHavePrePage ;  // 是否有上一页
	private boolean isHaveNextPage ; // 是否有下一页
	private List<T> pageDatas = new ArrayList<T>();

	public int getpageNo() {
	return pageNo;
	}

	public void setpageNo(int pageNo) {
	this.pageNo = pageNo;
	}

	public int getPageSize() {
	return pageSize;
	}

	public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
	}

	public int getTotalRecords() {
	return totalRecords;
	}


	public List<T> getPageDatas() {
	return pageDatas;
	}

	public void setPageDatas(List<T> pageDatas) {
	this.pageDatas = pageDatas;
	}

	public int getTotalPages() {
	return totalPages;
	}

	public boolean getIsHavePrePage() {
	return isHavePrePage;
	}

	public boolean getIsHaveNextPage() {
	return isHaveNextPage;
	}

	public PageBean(int pageNo, int pageSize, int totalRecords) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		if(totalRecords < 0){
			throw new RuntimeException("总记录数不能小于0!");
			}
			//设置总记录数
			this.totalRecords = totalRecords;
			//计算总页数
			this.totalPages = this.totalRecords/this.pageSize;
			if(this.totalRecords%this.pageSize!=0){
			this.totalPages++;
			}
			//计算是否有上一页
			if(this.pageNo>1){
			this.isHavePrePage = true;
			}else{
				this.isHavePrePage = false;
			}
			//计算是否有下一页
			if(this.pageNo<this.totalPages){
			this.isHaveNextPage = true;
			}else{
			this.isHaveNextPage = false;
			}
	}

	public PageBean() {
		super();
	}
	
	
}
