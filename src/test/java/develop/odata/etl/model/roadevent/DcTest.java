package develop.odata.etl.model.roadevent;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.Charsets;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class DcTest {
	private  ObjectMapper mapper;
	private XmlMapper xmlMapper;
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
//	@Ignore
	public void testReadXml20180217() throws Exception {
		String resource = "2018-02-17.xml";
		String xml = new String(Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI())),Charsets.UTF_8);
		System.out.println(xml);
		 
		Dc object = this.xmlMapper.readValue(xml, Dc.class);

		System.out.println(object);
		;
	}
}
