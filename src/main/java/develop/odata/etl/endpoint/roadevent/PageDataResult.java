package develop.odata.etl.endpoint.roadevent;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonSerialize(using = PageResultSerializer.class)
public class PageDataResult<T> extends java.util.ArrayList<T> {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "pageSize")
	private int pageSize;
	
	@JsonProperty(value = "totalRecord")
	private int totalRecord;
	
	@JsonProperty(value = "currPage")
	private int currPage;
	
	public PageDataResult() {
		super();
	}
	
	public PageDataResult(PageDataResult<?> result) {
		this.pageSize = result.getPageSize();
		this.totalRecord = result.getTotalRecord();
		this.currPage = result.getCurrPage();
	}
	

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
	public int getPageCount() {
		
		if (this.totalRecord % this.pageSize == 0) {
			return this.totalRecord / this.pageSize;
		} else {
			return (this.totalRecord / this.pageSize) +1;
		}
	}
	
}
