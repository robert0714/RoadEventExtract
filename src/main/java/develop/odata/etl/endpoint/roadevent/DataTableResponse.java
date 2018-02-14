package develop.odata.etl.endpoint.roadevent;

import java.util.List;

public class DataTableResponse<T> {

	/*
	 * Client送來的驗証碼
	 */
	public String sEcho;

	/*
	 * 未經過濾的資料總筆數
	 */
	public Integer iTotalRecords;

	/*
	 * 經過過濾的資料總筆數
	 */
	public Integer iTotalDisplayRecords;

	/*
	 * 回傳的資料
	 */
	public List<T> aaData;

	public DataTableResponse(String echo, PageDataResult<T> result) {

		this.sEcho = (echo == null ? "1" : echo);
		this.aaData = result;
		this.iTotalDisplayRecords = (int) result.getTotalRecord();
		this.iTotalRecords = (int) result.getTotalRecord();

	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public Integer getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

}
