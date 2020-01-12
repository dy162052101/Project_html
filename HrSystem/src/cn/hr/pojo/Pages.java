package cn.hr.pojo;

import java.util.List;

public class Pages {
	private int pageNum;//页数
	private int pageSize;//每页的大小
	private int totalCount;
	private int pageCount;
	private List<Department> list;
	private List<Position> list1;
	private List<Lea> list2;
	
	
	
	
	

	public Pages(int pageNum, int pageSize, int totalCount, int pageCount, List<Department> list,
			List<Position> list1,List<Lea>list2) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.list = list;
		this.list1 = list1;
		this.list2 = list2;
	}

	
	public Pages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<Department> getList() {
		return list;
	}
	public List<Position> getList1() {
		return list1;
	}
	public void setList(List<Department> list) {
		this.list = list;
	}
	public void setList1(List<Position> list) {
		this.list1 = list;
	}
	public List<Lea> getList2() {
		return list2;
	}
	public void setList2(List<Lea> list) {
		this.list2 = list;
	}
	
	

	
}
