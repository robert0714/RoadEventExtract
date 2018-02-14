package develop.odata.etl.endpoint.roadevent;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RoadEventRequestTest {

	private ObjectMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		this.mapper= new ObjectMapper();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testMethod()throws Exception  {
		RoadEventRequest request =new RoadEventRequest();
		request.setDes("Test");
		request.setEndDate(new Date());
		request.setStartDate(new Date());
		request.setRoadtype("sssssssssss");
		RoadEventPageRequest page= new RoadEventPageRequest (10,1);
//		request.setPage(page);
		String key = this.mapper.writeValueAsString(request);
		RoadEventRequest request1 =mapper.readValue(key, RoadEventRequest .class);
		
		System.out.println(request1);;
	}

}
