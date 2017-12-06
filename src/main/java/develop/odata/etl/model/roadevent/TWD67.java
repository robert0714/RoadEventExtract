package develop.odata.etl.model.roadevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "TWD67")
@JsonInclude(JsonInclude.Include.NON_EMPTY) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class TWD67 {
	@JacksonXmlProperty(localName = "TWD67X1")
	private String twd67X1;

	@JacksonXmlProperty(localName = "TWD67Y1")
	private String twd67Y1;

	@JacksonXmlProperty(localName = "TWD67X2")
	private String twd67X2;

	@JacksonXmlProperty(localName = "TWD67Y2")
	private String twd67Y2;

	public String getTwd67X1() {
		return twd67X1;
	}

	public void setTwd67X1(String twd67x1) {
		twd67X1 = twd67x1;
	}

	public String getTwd67Y1() {
		return twd67Y1;
	}

	public void setTwd67Y1(String twd67y1) {
		twd67Y1 = twd67y1;
	}

	public String getTwd67X2() {
		return twd67X2;
	}

	public void setTwd67X2(String twd67x2) {
		twd67X2 = twd67x2;
	}

	public String getTwd67Y2() {
		return twd67Y2;
	}

	public void setTwd67Y2(String twd67y2) {
		twd67Y2 = twd67y2;
	}
	
	
}
