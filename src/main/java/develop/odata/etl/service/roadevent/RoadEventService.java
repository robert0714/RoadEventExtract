package develop.odata.etl.service.roadevent;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
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

@Component
public class RoadEventService {

	private ObjectMapper xmlMapper;

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
		try {
			String content = this.xmlMapper.writeValueAsString(object);
			File outputD = new File(new URI(outputFolder));
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
			File outputfile =new File(dateD,prefix+".xml");
			
			FileUtils.writeStringToFile(outputfile, content);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Daily job.
	 * https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
	 */
	@Scheduled(cron="* */10 * * * *")
	public void dailyJob() {
		Dc data =  getRoadEvent();
		output(data);
	}
}
