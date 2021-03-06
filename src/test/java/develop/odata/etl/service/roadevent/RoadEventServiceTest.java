package develop.odata.etl.service.roadevent;

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

import develop.odata.etl.model.roadevent.Dc;
import develop.odata.etl.model.roadevent.Record;
import develop.odata.etl.model.roadevent.RoadData;

public class RoadEventServiceTest {

	private RoadEventService service;

	private  ObjectMapper mapper;
	private XmlMapper xmlMapper;

	boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

	@Before
	public void setUp() throws Exception {
		service = new RoadEventService();
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
	public void testReadXml() throws Exception {
		String resource = "/RoadData_LC.xml";
		String xml = new String(Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI())),Charsets.UTF_8);
		System.out.println(xml);
		 
		Dc object = this.xmlMapper.readValue(xml, Dc.class);

		System.out.println(object);
		;
	}
	@Test
//	@Ignore
	public void testReadXmlExportJson() throws Exception {
		String resource = "/RoadData_LC.xml";
		String xml = new String(Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI())),Charsets.UTF_8);		 
		Dc object = this.xmlMapper.readValue(xml, Dc.class);
		final String json = this.mapper.writeValueAsString(object);		
		System.out.println(json);
	}

	@Test
	@Ignore
	public void testReadXml2() throws Exception {
		String resource = "/RoadData_LC_data_set.xml";
		String xml = new String(Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI())),Charsets.UTF_8);
		System.out.println(xml);
		;

		RoadData object = this.xmlMapper.readValue(xml, RoadData.class);

		System.out.println(object);
		;
	}
	@Test
	@Ignore
	public void testReadXml2ExportJson() throws Exception {
		String resource = "/RoadData_LC_data_set.xml";
		String xml = new String(Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI())),Charsets.UTF_8);		
		RoadData object = this.xmlMapper.readValue(xml, RoadData.class);
		final String json = this.mapper.writeValueAsString(object);		
		System.out.println(json);
	}

	@Test
	@Ignore
	public void testReadXml3() throws Exception {
		String resource = "/RoadData_LC_data_unit.xml";
		String xml = new String(Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI())),Charsets.UTF_8);
		System.out.println(xml);
		Record object = this.xmlMapper.readValue(xml, Record.class);

		System.out.println(object);
		;
	}
	 
	@Test
	@Ignore
	public void testReadXml3ExportJson() throws Exception {
		String resource = "/RoadData_LC_data_unit.xml";
		String xml = new String(Files.readAllBytes(Paths.get(getClass().getResource(resource).toURI())),Charsets.UTF_8);
		
		Record object = this.xmlMapper.readValue(xml, Record.class);
		final String json = this.mapper.writeValueAsString(object);		
		System.out.println(json);
		;
	}

	@Test
	@Ignore
	public void testWriteXml() throws Exception {
		Dc object = new Dc();

//		object.setDate("2017/12/06 14:51:15");
		Record r1 = new Record();
		r1.setStatus("後續");
		r1.setRoad2(" -0 ");
		r1.setComment(" 自小客與機車發生事故 ");
		Record[] record = new Record[] { r1 };

		object.setRecord(record);

		String xml = this.xmlMapper.writeValueAsString(object);
		System.out.println(xml);
		;
	}

	@Test
	@Ignore
	public void testWriteUnitXml() throws Exception {

		Record r1 = new Record();
		r1.setNumber("073714");
		r1.setKeytime("14:33:00");
		r1.setStatus("後續");
		r1.setRegion("南部");
//		r1.setUpdatetime("2017/12/06 14:30:02");
		r1.setContinuetime("1900/01/01 00:00:00");
		r1.setDirection("");
		r1.setSpeedLow("0");
		r1.setSpeedTop("0");
		r1.setRoad1("-高雄市前鎮區中山四路與時代大道路0");
		r1.setRoad2(" -0 ");

		r1.setComment(" 自小客與機車發生事故 ");

		String xml = this.xmlMapper.writeValueAsString(r1);
		System.out.println(xml);
		;
	}


}
