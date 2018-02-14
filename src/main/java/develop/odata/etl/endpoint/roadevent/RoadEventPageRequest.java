package develop.odata.etl.endpoint.roadevent;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadEventPageRequest  implements Pageable, Serializable{
	
	@JsonProperty("page")
	private   int page;
	
	@JsonProperty("size")
	private   int size;
	
	@JsonProperty("sort")
	private   Sort sort;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3949663995986733661L;

	@JsonIgnore
	private PageRequest origin ;
	
	
	@JsonCreator
	public RoadEventPageRequest(@JsonProperty("page") int page, @JsonProperty("size") int size) {
		super( );
		this.page=page;
		this.size =size; 
		origin = new PageRequest(page, size);
	} 
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page; 
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size; 
	} 
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	@JsonIgnore
	@Override
	public int getPageSize() {
		// TODO Auto-generated method stub
		return origin.getPageSize();
	}
	@JsonIgnore
	@Override
	public int getPageNumber() {
		// TODO Auto-generated method stub
		return origin.getPageNumber();
	}
	@JsonIgnore
	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return origin.getOffset();
	}

	@Override
	public Pageable next() {
		// TODO Auto-generated method stub
		return origin.next();
	}

	@Override
	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return origin.previousOrFirst();
	}

	@Override
	public Pageable first() {
		// TODO Auto-generated method stub
		return origin.first();
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return origin.hasPrevious();
	}

}
