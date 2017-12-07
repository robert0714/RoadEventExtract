package develop.odata.etl.service.roadevent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
 
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
	public void filter(final Dc object) {
		List<Record> totalList = new LinkedList<>();
		final Record[] src = object.getRecord();
		for(Record r :src ) {
			if(StringUtils.contains( r.getRegion(), "北部") ||StringUtils.contains( r.getRegion() , "A" )) {
				totalList.add(r);
			}
		}
		object.setRecord(totalList.toArray(new Record[] {}));
	}
	public HSSFSheet convertLine(final Dc object,HSSFWorkbook wb ) {
		SimpleDateFormat sdf =new  SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat sdf1 =new  SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
		final HSSFSheet sheet = wb.createSheet(sdf1.format(object.getDate()));
		HSSFRow row = sheet.createRow(0);

		HSSFCell cellTitle = row.createCell(0);
		cellTitle.setCellValue("number");

		HSSFCell cellDescription = row.createCell(1);
		cellDescription.setCellValue("keytime");

		HSSFCell cellAuthor = row.createCell(2);
		cellAuthor.setCellValue("status");

		HSSFCell cellPubDate = row.createCell(3);
		cellPubDate.setCellValue("region");

		HSSFCell cellStartDate = row.createCell(4);
		cellStartDate.setCellValue("updatetime");

		HSSFCell cellEndDate = row.createCell(5);
		cellEndDate.setCellValue("roadtype");
		
		HSSFCell cell6 = row.createCell(6);
		cell6.setCellValue("happentime");
		
		HSSFCell cell7 = row.createCell(7);
		cell7.setCellValue("continuetime");
		
		HSSFCell cell8 = row.createCell(8);
		cell8.setCellValue("speedLow");
		
		HSSFCell cell9 = row.createCell(9);
		cell9.setCellValue("speedTop");
		
		HSSFCell cell10 = row.createCell(10);
		cell10.setCellValue("road1");
		
		HSSFCell cell11 = row.createCell(11);
		cell11.setCellValue("road2");
		
		HSSFCell cell12 = row.createCell(12);
		cell12.setCellValue("comment");
		
		HSSFCell cell13 = row.createCell(13);
		cell13.setCellValue("messageSrc");
		
		HSSFCell cell14 = row.createCell(14);
		cell14.setCellValue("srcDetail");
		
		HSSFCell cell15= row.createCell(15);
		cell15.setCellValue("canceltime");
		
		HSSFCell cell16 = row.createCell(16);
		cell16.setCellValue("gps-x1");
		
		HSSFCell cell17 = row.createCell(17);
		cell17.setCellValue("gps-y1");
		
		HSSFCell cell18 = row.createCell(18);
		cell18.setCellValue("twd67X1");
		
		HSSFCell cell19 = row.createCell(19);
		cell19.setCellValue("twd67Y1");
		
		HSSFCell cell20 = row.createCell(20);
		cell20.setCellValue("name");
		
		HSSFCell cell21 = row.createCell(21);
		cell21.setCellValue("level");
		 
		int i = 0;
		for(Record r : object.getRecord()) {
			++i;
			HSSFRow eachRow = sheet.createRow(i);
			
			HSSFCell cell0= eachRow.createCell(0);cell0.setCellValue(r.getNumber());			
			HSSFCell cell1= eachRow.createCell(1); cell1.setCellValue(r.getKeytime());
			HSSFCell cell2= eachRow.createCell(2); cell2.setCellValue(r.getStatus());
			HSSFCell cell3= eachRow.createCell(3); cell3.setCellValue(r.getRegion());
			HSSFCell cell4= eachRow.createCell(4); cell4.setCellValue(r.getUpdatetime());
			HSSFCell cell5= eachRow.createCell(5); cell5.setCellValue(r.getRoadtype());
			HSSFCell celle6= eachRow.createCell(6); celle6.setCellValue(r.getHappentime());
			HSSFCell celle7= eachRow.createCell(7); celle7.setCellValue(r.getContinuetime());
			HSSFCell celle8= eachRow.createCell(8); celle8.setCellValue(r.getSpeedLow());
			HSSFCell celle9= eachRow.createCell(9); celle9.setCellValue(r.getSpeedTop());
			HSSFCell celle10= eachRow.createCell(10); celle10.setCellValue(r.getRoad1());
			HSSFCell celle11= eachRow.createCell(11); celle11.setCellValue(r.getRoad2());
			HSSFCell celle12= eachRow.createCell(12); celle12.setCellValue(r.getComment());
			HSSFCell celle13= eachRow.createCell(13); celle13.setCellValue(r.getMessageSrc());
			HSSFCell celle14= eachRow.createCell(14); celle14.setCellValue(r.getSrcDetail());
			HSSFCell celle15= eachRow.createCell(15); celle15.setCellValue(r.getCanceltime());
			HSSFCell celle16= eachRow.createCell(16); celle16.setCellValue(r.getGps().getX1());
			HSSFCell celle17= eachRow.createCell(17); celle17.setCellValue(r.getGps().getY1());
			HSSFCell celle18= eachRow.createCell(18); celle18.setCellValue(r.getTwd67().getTwd67X1());
			HSSFCell celle19= eachRow.createCell(19); celle19.setCellValue(r.getTwd67().getTwd67Y1());
			HSSFCell celle20= eachRow.createCell(20); celle20.setCellValue(r.getName());
			HSSFCell celle21= eachRow.createCell(21); celle21.setCellValue(r.getLevel());

		}
		return sheet;
	}
	public void output(Dc object) {
		filter(object);		
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
			File outputfile =new File(dateD,prefix+".xlsx");
			
//			FileUtils.writeStringToFile(outputfile, content);
			
			if(object == null ) {
				return;
			}
			try (HSSFWorkbook wb = new HSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream(outputfile);) {
				convertLine(object, wb);
				// write this workbook to an Outputstream.
				wb.write(fileOut);
				fileOut.flush();
			}
			
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
