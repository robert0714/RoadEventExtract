package develop.odata.etl.domain.roadevent;

import java.util.Date;
 
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude; 

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true,value= {"updatetime"})
@XmlRootElement
@Relation(collectionRelation="record")
//@QueryEntity
@Document
public class Record {

	public Record(String number, Date updatetime, Date happentime, String roadtype, String road1, String comment,
			String des, String srcDetail, String name, String gpsX1, String gpsY1) {
		super();
		this.number = number;
		this.updatetime = updatetime;
		this.happentime = happentime;
		this.roadtype = roadtype;
		this.road1 = road1;
		this.comment = comment;
		this.des = des;
		this.srcDetail = srcDetail;
		this.name = name;
		this.gpsX1 = gpsX1;
		this.gpsY1 = gpsY1;
	}
	
	@JsonCreator
	public Record( ) {
		super(); 
	}

	// 相當於id
	@Id
	private String number;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonIgnore
	private Date updatetime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
	private Date happentime;

	private String roadtype;
	
	private String road1;
	
	private String comment;
	
	/**
	 * 合併road1與comment的資料updatetime
	 * **/	
	@Indexed
	private String des;

	private String srcDetail;

	private String name;
	
	private String gpsX1;

	private String gpsY1;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@JsonIgnore
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getHappentime() {
		return happentime;
	}

	public void setHappentime(Date happentime) {
		this.happentime = happentime;
	}

	public String getRoadtype() {
		return roadtype;
	}

	public void setRoadtype(String roadtype) {
		this.roadtype = roadtype;
	}

	public String getRoad1() {
		return road1;
	}

	public void setRoad1(String road1) {
		this.road1 = road1;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getSrcDetail() {
		return srcDetail;
	}

	public void setSrcDetail(String srcDetail) {
		this.srcDetail = srcDetail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGpsX1() {
		return gpsX1;
	}

	public void setGpsX1(String gpsX1) {
		this.gpsX1 = gpsX1;
	}

	public String getGpsY1() {
		return gpsY1;
	}

	public void setGpsY1(String gpsY1) {
		this.gpsY1 = gpsY1;
	}

	
}
