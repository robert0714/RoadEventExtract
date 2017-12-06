package develop.odata.etl.model.roadevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JsonInclude(JsonInclude.Include.NON_EMPTY) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class Record {

	private String number;
	private String keytime;
	private String status;
	private String region;
	private String updatetime;
	private String roadtype;
	private String happentime;
	private String continuetime;

	@JacksonXmlProperty(localName = "Direction")
	private String direction;

	@JacksonXmlProperty(localName = "SpeedLow")
	private String speedLow;

	@JacksonXmlProperty(localName = "SpeedTop")
	private String speedTop;

	@JacksonXmlCData
	@JacksonXmlProperty(localName = "road1")
	protected String road1;

	@JacksonXmlProperty(localName = "From1")
	private String from1;

	@JacksonXmlProperty(localName = "To1")
	private String to1;

	@JacksonXmlCData
	@JacksonXmlProperty(localName = "road2")
	protected String road2;

	@JacksonXmlProperty(localName = "From2")
	private String from2;

	@JacksonXmlProperty(localName = "To2")
	private String to2;

	@JacksonXmlCData
	@JacksonXmlProperty(localName = "Comment")
	protected String comment;

	@JacksonXmlProperty(localName = "MessageSrc")
	private String messageSrc;

	@JacksonXmlProperty(localName = "SrcDetail")
	private String srcDetail;

	private String canceltime;

	@JacksonXmlProperty(localName = "GPS")
	private GPS gps ; 
	
	
	@JacksonXmlProperty(localName = "TWD67")
	private TWD67 twd67;

	

	private String name;
	private String station_sn;
	private String area_sn;
	private String area;

	@JacksonXmlProperty(localName = "FromKM")
	private String fromKM;

	@JacksonXmlProperty(localName = "ToKM")
	private String toKM;

	@JacksonXmlProperty(localName = "Level")
	private String level;

	@JacksonXmlProperty(localName = "Affect")
	private String affect;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getKeytime() {
		return keytime;
	}

	public void setKeytime(String keytime) {
		this.keytime = keytime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getRoadtype() {
		return roadtype;
	}

	public void setRoadtype(String roadtype) {
		this.roadtype = roadtype;
	}

	public String getHappentime() {
		return happentime;
	}

	public void setHappentime(String happentime) {
		this.happentime = happentime;
	}

	public String getContinuetime() {
		return continuetime;
	}

	public void setContinuetime(String continuetime) {
		this.continuetime = continuetime;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSpeedLow() {
		return speedLow;
	}

	public void setSpeedLow(String speedLow) {
		this.speedLow = speedLow;
	}

	public String getSpeedTop() {
		return speedTop;
	}

	public void setSpeedTop(String speedTop) {
		this.speedTop = speedTop;
	}

	public String getRoad1() {
		return road1;
	}

	public void setRoad1(String road1) {
		this.road1 = road1;
	}

	public String getFrom1() {
		return from1;
	}

	public void setFrom1(String from1) {
		this.from1 = from1;
	}

	public String getTo1() {
		return to1;
	}

	public void setTo1(String to1) {
		this.to1 = to1;
	}

	public String getRoad2() {
		return road2;
	}

	public void setRoad2(String road2) {
		this.road2 = road2;
	}

	public String getFrom2() {
		return from2;
	}

	public void setFrom2(String from2) {
		this.from2 = from2;
	}

	public String getTo2() {
		return to2;
	}

	public void setTo2(String to2) {
		this.to2 = to2;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMessageSrc() {
		return messageSrc;
	}

	public void setMessageSrc(String messageSrc) {
		this.messageSrc = messageSrc;
	}

	public String getSrcDetail() {
		return srcDetail;
	}

	public void setSrcDetail(String srcDetail) {
		this.srcDetail = srcDetail;
	}

	public String getCanceltime() {
		return canceltime;
	}

	public void setCanceltime(String canceltime) {
		this.canceltime = canceltime;
	}

 

	public GPS getGps() {
		return gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}

	public TWD67 getTwd67() {
		return twd67;
	}

	public void setTwd67(TWD67 twd67) {
		this.twd67 = twd67;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStation_sn() {
		return station_sn;
	}

	public void setStation_sn(String station_sn) {
		this.station_sn = station_sn;
	}

	public String getArea_sn() {
		return area_sn;
	}

	public void setArea_sn(String area_sn) {
		this.area_sn = area_sn;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFromKM() {
		return fromKM;
	}

	public void setFromKM(String fromKM) {
		this.fromKM = fromKM;
	}

	public String getToKM() {
		return toKM;
	}

	public void setToKM(String toKM) {
		this.toKM = toKM;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAffect() {
		return affect;
	}

	public void setAffect(String affect) {
		this.affect = affect;
	}

	
}
