package develop.odata.etl.endpoint.roadevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDataRequest {
	
	/** 每頁顯示筆數  */
	@JsonProperty
	protected int pageSize = 5;
	
	/** 顯示第幾頁 */
	@JsonProperty
	protected int pageNo = 1;
	
	
	public PageDataRequest() {
		super();
	}
	
	public PageDataRequest(int pageNo) {
		super();
		this.pageNo = pageNo;
	}
	
	public PageDataRequest(int pageNo,int pageSize) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}
	
	/**
	 * 取得每頁顯示筆數
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 設定每頁顯示筆數
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 取得顯示第幾頁
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 設定顯示第幾頁
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	

	public int getStartIndex() {
		return (this.pageNo - 1) * this.pageSize + 1;
	}
	
	public int getEndIndex() {
		return (this.pageNo) * this.pageSize;
	}
	
	public String toString() {
		
		return "pageSize:" + this.pageSize + " pageNo:" + this.pageNo + " start:" + this.getStartIndex() + " end:" + this.getEndIndex(); 
				
	}
	
}
