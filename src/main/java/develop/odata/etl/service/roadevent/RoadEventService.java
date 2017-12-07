package develop.odata.etl.service.roadevent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import develop.odata.etl.model.roadevent.Dc;
import develop.odata.etl.model.roadevent.Record;

@Component
public class RoadEventService {

	private ObjectMapper xmlMapper;
	private ObjectMapper objectMapper;

	private RestTemplate restTemplate;

	private String resourceUrl = "http://61.57.40.124/eTrafficXML/RoadData_LC.xml";
	
	@Value("${develop.odata.etl.services.roadEvents.data}")
	private String outputFolder ;

	@PostConstruct
	public void postConstruct() {
		restTemplate = new RestTemplate();
		JacksonXmlModule module = new JacksonXmlModule();
		module.setDefaultUseWrapper(false);
		this.xmlMapper = new XmlMapper(module);
		// this.xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
		this.objectMapper = new ObjectMapper();
	}

	public Dc getRoadEvent() {
		ResponseEntity<Dc> response = restTemplate.getForEntity(resourceUrl, Dc.class);
		Dc body = response.getBody();
		return body;
	}

	public String getXmlFormObject(Dc object) throws JsonProcessingException {
		return this.xmlMapper.writeValueAsString(object);
	}
	
	public void output(Dc object) {
		List<Record> totalList = new LinkedList<>();
		final Record[] src = object.getRecord();
		for(Record r :src ) {
			if(StringUtils.contains( r.getRegion(), "北部") ||StringUtils.contains( r.getRegion() , "A" )) {
				totalList.add(r);
			}
		}
		object.setRecord(totalList.toArray(new Record[] {}));
		;
		try {
			String content = null;
			if(object != null ) {
				content = this.objectMapper .writeValueAsString(object);
			}else {
				content = "test";
			}
			 
			File outputD = new File( outputFolder );
			if (!outputD.exists()) {
				outputD.mkdirs();
			}
			SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
			File dateD = new File(outputD, sdf0.format(new Date()));
			if (!dateD.exists()) {
				dateD.mkdirs();
			}
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
			String prefix = sdf1.format(new Date());
			File outputfile =new File(dateD,prefix+".json");
			
			FileUtils.writeStringToFile(outputfile, content);
		}  catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Daily job.
	 * https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
	 */
//	@Scheduled(cron="* */5 * * * *")
	@Scheduled(cron="*/10 * * * * *")
	public void dailyJob() {
		Dc data =  getRoadEvent();
		output(data);
//		output(null);
	}
}
