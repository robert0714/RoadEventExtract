package develop.odata.etl.endpoint.roadevent;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.CacheControl; 
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;  
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController;
  
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import develop.odata.etl.domain.roadevent.Record;
import develop.odata.etl.service.roadevent.RoadEventService; 

@RestController
@RequestMapping(value = "/roadEvent")
public class RoadEventEndpoint {

	@Autowired
	private RoadEventService service;
	private ObjectMapper mapper;

	/** The logger. */
	private final Logger LOGGER = LoggerFactory.getLogger(RoadEventEndpoint.class);
	private static String ALL = "ALL";

	@PostConstruct
	public void postConstruct() {
		this.mapper= new ObjectMapper();
	}
	/***
	 * server快取
	 */
	private LoadingCache<String, Record[]> cacheData = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterAccess(5, TimeUnit.MINUTES).build(new CacheLoader<String, Record[]>() {
				@Override
				public Record[] load(String searchData) throws Exception {
					List<Record> result = service.findToday(); 
					
					return result.toArray(new Record[] {});
				}
			});
 
	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<Record[]> listdata() {
		Record[] data = null;
		try {
			data = cacheData.get(ALL);
		} catch (ExecutionException e) {
			LOGGER.warn("An exception occurred while " + "fetching place details!", e.getCause());
			return null;
		} 
		
		/***
		 * client cache
		 */
		final	CacheControl cc = CacheControl.maxAge(5l,TimeUnit.MINUTES);
		
		final ResponseEntity<Record[]> result = ResponseEntity.ok().cacheControl(cc).body(data);
		return result;
	}

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value = "/search")
	public ResponseEntity<Slice<Record>> search(@RequestParam(value = "rt", defaultValue = "", required = false) String roadtype,
			@RequestParam(value = "des", defaultValue = "", required = false) String des,
			@RequestParam(value = "start_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "end_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@PageableDefault(size = 10, page = 0, sort = {
					"happentime" }, direction = Direction.DESC) Pageable pageable) {

		Slice<Record> data = service.find(roadtype, des, startDate, endDate, pageable);


		/***
		 * client cache
		 */
		final	CacheControl cc = CacheControl.maxAge(5l,TimeUnit.MINUTES);
		
		final ResponseEntity<Slice<Record>> result = ResponseEntity.ok().cacheControl(cc).body(data);
		 
		
		return result;
	}
}
