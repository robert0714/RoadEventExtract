package develop.odata.etl.model.roadevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "Road_data"  ) 
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadData {
	@JacksonXmlElementWrapper(localName = "Road_data", useWrapping = false)
	@JacksonXmlProperty(localName = "Record")
	private Record[] record;

	public Record[] getRecord() {
		return record;
	}

	public void setRecord(Record[] record) {
		this.record = record;
	}
	

}
