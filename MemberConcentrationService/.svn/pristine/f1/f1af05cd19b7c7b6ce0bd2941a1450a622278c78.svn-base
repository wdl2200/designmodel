package com.cmts.xm.dao.util;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author susx
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class PageView<T> implements Serializable {
	private List<T> records; // 当前页的分页数据
	private long totalPages = 1; // 总页数
	private int maxResult = 2; // 每页显示记录数
	private int currentPage = 1; // 当前页
	private long totalRecords; //总记录数
	private int pageCode = 10; //每页显示的页码数量，默认10条
	
	private String sortFile;
	private String sortType;
	
	public String getSortFile() {
		return sortFile;
	}

	public void setSortFile(String sortFile) {
		this.sortFile = sortFile;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public PageView() {}
	
	public PageView(int maxResult, int currentPage) {
		this.maxResult = maxResult;
		this.currentPage = currentPage;
	}
	
	/**
	 * 获取记录的开始索引
	 * @return
	 */
	public int getStartIndex() {
		return (this.currentPage - 1) * this.maxResult;
	}
	/**
	 * 要读取显示的记录数
	 * @return
	 */
	public int getPageResult(){
		return this.currentPage * this.maxResult;
	}

	public int getPageCode() {
		return pageCode;
	}

	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public int getCurrentPage() {
		return currentPage;
	}


	public long getTotalRecords() {
		return totalRecords;
	}
    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
        setTotalPages(this.totalRecords % this.maxResult == 0 ? this.totalRecords / this.maxResult : this.totalRecords / this.maxResult + 1); 
    }
}
