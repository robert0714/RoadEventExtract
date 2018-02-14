package develop.odata.etl.endpoint.roadevent;

import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadEventRequest {

	private String roadtype;

	private String des;

	@JsonProperty("startDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
	private Date startDate;

	@JsonProperty("endDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
	private Date endDate;
	
	
	@JsonProperty(value = "sEcho")
	private String echo;

	@JsonProperty(value = "iColumns")
	private Integer numColumns;

	@JsonProperty(value = "sColumns")
	private String columns;

	@JsonProperty(value = "iDisplayStart")
	private Integer displayStart;

	@JsonProperty(value = "iDisplayLength")
	private Integer displayLength;

	// has to be revisited for Object type dataProps.
	@JsonProperty(value = "amDataProp")
	private List<String> dataProp;

	@JsonProperty(value = "sSearch")
	private String searchQuery;

	@JsonProperty(value = "asSearch")
	private List<String> columnSearches;

	@JsonProperty(value = "bRegex")
	private boolean hasRegex;

	@JsonProperty(value = "abRegex")
	private List<Boolean> regexColumns;

	@JsonProperty(value = "abSearchable")
	private List<Boolean> searchColumns;

	@JsonProperty(value = "iSortingCols")
	private Integer sortingCols;

	@JsonProperty(value = "aiSortCol")
	private List<Integer> sortedColumns;

	@JsonProperty(value = "asSortDir")
	private List<String> sortDirections;

	@JsonProperty(value = "abSortable")
	private List<Boolean> sortableColumns;
	
	
	/** 每頁顯示筆數  */
	@JsonProperty
	private Integer pageSize  ;
	
	/** 顯示第幾頁 */
	@JsonProperty
	private Integer pageNo  ;
	
//	@JsonProperty("page")
//	private RoadEventPageRequest page;

	public String getRoadtype() {
		return roadtype;
	}

	public void setRoadtype(String roadtype) {
		this.roadtype = roadtype;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEcho() {
		return echo;
	}

	public void setEcho(String echo) {
		this.echo = echo;
	}

	public Integer getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(Integer numColumns) {
		this.numColumns = numColumns;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public Integer getDisplayStart() {
		return displayStart;
	}

	public void setDisplayStart(Integer displayStart) {
		this.displayStart = displayStart;
	}

	public Integer getDisplayLength() {
		return displayLength;
	}

	public void setDisplayLength(Integer displayLength) {
		this.displayLength = displayLength;
	}

	public List<String> getDataProp() {
		return dataProp;
	}

	public void setDataProp(List<String> dataProp) {
		this.dataProp = dataProp;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List<String> getColumnSearches() {
		return columnSearches;
	}

	public void setColumnSearches(List<String> columnSearches) {
		this.columnSearches = columnSearches;
	}

	public boolean isHasRegex() {
		return hasRegex;
	}

	public void setHasRegex(boolean hasRegex) {
		this.hasRegex = hasRegex;
	}

	public List<Boolean> getRegexColumns() {
		return regexColumns;
	}

	public void setRegexColumns(List<Boolean> regexColumns) {
		this.regexColumns = regexColumns;
	}

	public List<Boolean> getSearchColumns() {
		return searchColumns;
	}

	public void setSearchColumns(List<Boolean> searchColumns) {
		this.searchColumns = searchColumns;
	}

	public Integer getSortingCols() {
		return sortingCols;
	}

	public void setSortingCols(Integer sortingCols) {
		this.sortingCols = sortingCols;
	}

	public List<Integer> getSortedColumns() {
		return sortedColumns;
	}

	public void setSortedColumns(List<Integer> sortedColumns) {
		this.sortedColumns = sortedColumns;
	}

	public List<String> getSortDirections() {
		return sortDirections;
	}

	public void setSortDirections(List<String> sortDirections) {
		this.sortDirections = sortDirections;
	}

	public List<Boolean> getSortableColumns() {
		return sortableColumns;
	}

	public void setSortableColumns(List<Boolean> sortableColumns) {
		this.sortableColumns = sortableColumns;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}


}
