package develop.odata.etl.model.roadevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "govtwsimpledc")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dc {
	@JacksonXmlProperty(localName = "dc.date")
	private String date;

	@JacksonXmlProperty(localName = "dc.subject")
	private String subject;

	@JacksonXmlProperty(localName = "dc.title")
	private String title;

	@JacksonXmlElementWrapper(localName = "Road_data", useWrapping = true)
	@JacksonXmlProperty(localName = "Record")
	private Record[] record;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Record[] getRecord() {
		return record;
	}

	public void setRecord(Record[] record) {
		this.record = record;
	}

}
