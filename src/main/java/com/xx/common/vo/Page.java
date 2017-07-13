package com.xx.common.vo;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7551637626432156366L;
	
	private long pageSize = 20; 
	private long pageIndex = 0; 
	private long total = 0; 
	
	private List<?> data;
	
	private Class<?> returnclass;
	
	/**
	 * 获取总页数
	 * @return
	 */
	public long getPageCount() {
		if (total % pageSize == 0) {
            return total / pageSize;
        } else {
        	return (total / pageSize) + 1;
        }
	}
	
	/**
	 * 获取当前数据集大小
	 * @return
	 */
	public int getSize() {
		if(data!=null)return data.size();
		return 0;
	}
	

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public Class<?> getReturnclass() {
		return returnclass;
	}

	public void setReturnclass(Class<?> returnclass) {
		this.returnclass = returnclass;
	}

	
}
