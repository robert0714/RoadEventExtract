package develop.odata.etl.endpoint.roadevent;
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate; 
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner; 

import com.fasterxml.jackson.databind.ObjectMapper;

import develop.odata.etl.Application; 

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT ,classes=Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoadEventEndpointTest2 {
	/** The rest template. */
	@Autowired
	private TestRestTemplate restTemplate;
	 
	 
	
	private ObjectMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		this.mapper= new ObjectMapper();
	}
	

	@After
	public void tearDown() throws Exception {
	}
	@Test
	@Ignore
	public void testDynamicRecord() throws Exception {
		String uri="/roadEvent/search";
		
		
		ResponseEntity<String> response = this.restTemplate.getForEntity(uri,  String.class);	
		System.out.println(response.getBody());;
		
	} 
	@Test
	@Ignore
	public void testDynamicRecord2() throws Exception {
		String uri="/roadEvent/search";
		RoadEventRequest request = new RoadEventRequest();
		
		
		request.setPageNo(1);
		request.setPageSize(1);
		ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request , String.class);	
		System.out.println(response.getBody());;
		
	}  
	@Test
//	@Ignore
	public void testDynamicRecord3() throws Exception {
		String uri="/roadEvent/search";
		String resource = "/request.json";
		String json = new String(Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI())),Charsets.UTF_8);	
		RoadEventRequest request = this.mapper.readValue(json, RoadEventRequest.class);
		
		
		request.setPageNo(1);
		request.setPageSize(1);
		ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request , String.class);	
		System.out.println(response.getBody());
		System.out.println(response.getBody());
	} 
	@Test
	@Ignore
	public void testDivide() throws Exception {
		 BigDecimal bg1, bg2, bg3;

	      bg1 = new BigDecimal("16");
	      bg2 = new BigDecimal("3");

	      // divide bg1 with bg2 with 3 scale
	      bg3 = bg1.divide(bg2,0,RoundingMode.UP );

	      String str = "Division result is " +bg3;

	      // print bg3 value
	      System.out.println( str );
	      
	      System.out.println( divide(16,3)   ) ;
	      // print bg3 value
	      System.out.println( str );
	} 
	int divide(Integer data ,Integer divider   ) {
		BigDecimal	  bg1 = new BigDecimal(data);
		BigDecimal     bg2 = new BigDecimal(divider);
		BigDecimal     bg3 = bg1.divide(bg2,0,RoundingMode.UP );
		return bg3.intValue();
	}
}
