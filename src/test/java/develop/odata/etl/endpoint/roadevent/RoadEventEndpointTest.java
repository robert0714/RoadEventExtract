package develop.odata.etl.endpoint.roadevent;
 
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import develop.odata.etl.Application;
import develop.odata.etl.domain.roadevent.Record;
import develop.odata.etl.service.roadevent.RoadEventService; 
 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT ,classes=Application.class)
public class RoadEventEndpointTest {
	/** The rest template. */
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private RoadEventEndpoint endpoint ;
	
	
//	@Autowired
//	private RoadEventService service ;
	
	@Before
	public void setUp() throws Exception {
		RoadEventService mockService =mock(RoadEventService.class);
		List<Record> value =new ArrayList<Record>();
		Record sample =new Record();
		sample.setComment("光復南路與信義路的交叉路口.號誌異常 光復閃黃 信義閃紅");
		sample.setGpsX1("121.55738");
		sample.setGpsY1("25.03321");
		sample.setName("燈號不正常");
		
		value.add(sample);
		when(mockService.findAll()).thenReturn(value);
		ReflectionTestUtils.setField(endpoint, "service", mockService);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testRecord() throws Exception {
		String uri="/roadEvent";
		
		
		ResponseEntity<String> response = this.restTemplate.getForEntity(uri,  String.class);	
		System.out.println(response.getBody());;
		Assert.assertEquals("[{\"comment\":\"光復南路與信義路的交叉路口.號誌異常 光復閃黃 信義閃紅\",\"name\":\"燈號不正常\",\"gpsX1\":\"121.55738\",\"gpsY1\":\"25.03321\"}]", response.getBody());
	} 
	 
}
