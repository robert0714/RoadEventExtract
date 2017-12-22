package develop.odata.etl.repository.roadevent;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.junit4.SpringRunner;

import develop.odata.etl.Application;
import develop.odata.etl.domain.roadevent.Record;
import develop.odata.etl.model.roadevent.Dc;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT ,classes=Application.class)
public class RecordRepositoryTest  {
	
	@Autowired
	private RecordRepository repo ;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test 
	@Ignore
	public void testNameContain() throws Exception {
		Record r1 =new Record();
		r1.setDes("中正");
		Example<Record> example = Example.of(r1, matching(). //
				withStringMatcher(StringMatcher.CONTAINING)) ;
		
		List<Record> data1 = repo.findAll(example);
		System.out.println(data1.size());;
	}
	@Test 
	public void testNameContain2() throws Exception {
		Record r1 =new Record();
		r1.setDes("中正");
		String roadType =null;
		Example<Record> ex = Example.of(r1, matching(). //
				withStringMatcher(StringMatcher.CONTAINING)) ;
		
		List<Record> data0 = repo.findAll(ex );
		System.out.println(data0.size());;
		
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2017-12-08");
		
		Date startDate =date;
		Date endDate =DateUtils.addSeconds(DateUtils.addDays(date, 1), -1);
		Pageable pageable=Mockito.mock(Pageable.class) ;
		Mockito.when(pageable.getPageNumber()).thenReturn(0);
		Mockito.when(pageable.getPageSize()).thenReturn(3);
		ex = Example.of(r1, matching(). //
				withStringMatcher(StringMatcher.CONTAINING)) ;
		Slice<Record> data1 = repo.findAll(ex, pageable);
		List<Record> list1 = data1.getContent();
		
		
		
		Slice<Record> data4 = repo.findByDesLikeAndHappentimeBetween("中正",startDate, endDate,pageable);
		
		List<Record> list4  = data4.getContent();
		
		System.out.println(list4.size());;
	}
	

}
