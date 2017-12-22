package develop.odata.etl.service.roadevent;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.Charsets;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import develop.odata.etl.Application;
import develop.odata.etl.model.roadevent.Dc;
import develop.odata.etl.model.roadevent.Record;
import develop.odata.etl.model.roadevent.RoadData;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT ,classes=Application.class)
public class RoadEventServiceTest2 {
	
	@Autowired
	private RoadEventService service;

	private  ObjectMapper mapper;
	private XmlMapper xmlMapper;

	boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

	@Before
	public void setUp() throws Exception { 
		JacksonXmlModule module = new JacksonXmlModule();
		module.setDefaultUseWrapper(false);
		this.xmlMapper = new XmlMapper(module);
		// this.xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
		this.mapper= new ObjectMapper();
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	@Ignore
	public void testReadJson() throws Exception {
		String resource = "/2017-12-07-22-34.json";
		String json = new String(Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI())),Charsets.UTF_8);		 
		Dc object = this.mapper.readValue(json, Dc.class);
		service.storePersistent(object);
	}

	
}
