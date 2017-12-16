package develop.odata.etl.endpoint.roadevent;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import develop.odata.etl.domain.roadevent.Record;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadEventResponse {
	private List<Record> data;

	public List<Record> getData() {
		return data;
	}

	public void setData(List<Record> data) {
		this.data = data;
	}
	
}
