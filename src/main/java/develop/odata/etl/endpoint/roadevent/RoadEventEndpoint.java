package develop.odata.etl.endpoint.roadevent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

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

	/** The logger. */
	private final Logger LOGGER = LoggerFactory.getLogger(RoadEventEndpoint.class);
	private static String ALL = "ALL";
 
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
	@RequestMapping(value = "/rightnow", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void rightnowGetData() {
		this.service.dailyJob();
		this.cacheData.cleanUp();
		LOGGER.info("clean cache successfully");
	}

	@CrossOrigin(origins = {"*"})
	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE } )
	public DeferredResult< ResponseEntity<Record[]>> listdata() {
		final DeferredResult< ResponseEntity<Record[]>> result= new DeferredResult<>();	
		new Thread(new Runnable() {
			@Override
			public void run() {
				Record[] data = null;
				try {
					data = cacheData.get(ALL);
				} catch (ExecutionException e) {
					LOGGER.warn("An exception occurred while " + "fetching place details!", e.getCause());

				}
				/***
				 * client cache
				 */
				final CacheControl cc = CacheControl.maxAge(5l, TimeUnit.MINUTES);

				final ResponseEntity<Record[]> initresult = ResponseEntity.ok().cacheControl(cc).body(data);
				result.setResult(initresult);
			}
		}
		).start();		
		return result;
	}

//	@CrossOrigin(origins = {"*"})
	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value = "/searchv1")
	public ResponseEntity<Page<Record>> search(@RequestParam(value = "rt", defaultValue = "", required = false) String roadtype,
			@RequestParam(value = "des", defaultValue = "", required = false) String des,
			@RequestParam(value = "start_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "end_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@PageableDefault(size = 10, page = 0, sort = {
					"happentime" }, direction = Direction.DESC) Pageable pageable) {

		Page<Record> data = service.find(roadtype, des, startDate, endDate, pageable);
		 
		/***
		 * client cache
		 */
		final	CacheControl cc = CacheControl.maxAge(5l,TimeUnit.MINUTES);
		
		final ResponseEntity<Page<Record>> result = 
				ResponseEntity.ok().
				cacheControl(cc).
				header(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "86400").
				header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST").
				body(data);
		 
		
		return result;
	}
	 
	protected  DataTableResponse<Record> convert( Page<Record> data) { 
		PageDataResult<Record> result = new PageDataResult<Record>();
		final List<Record> list = data.getContent();
		//發現有資料的屬性name是null
		for(Record unit:list ) {
			if(unit.getName() == null ) {
				unit.setName("");
			}
		}
		result.addAll(list);
		result.setCurrPage(data.getNumber());
		result.setTotalRecord((int)data.getTotalElements());
		result.setPageSize(data.getSize()); 
		
		return new DataTableResponse<Record>(String.valueOf(data.getNumber()+1), result);
	}
	int divide(Integer data ,Integer divider   ) {
		BigDecimal	  bg1 = new BigDecimal(data);
		BigDecimal     bg2 = new BigDecimal(divider);
		BigDecimal     bg3 = bg1.divide(bg2,0,RoundingMode.UP );
		return bg3.intValue();
	}
	@CrossOrigin(origins = {"*"})
	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value = "/search")
	public ResponseEntity<DataTableResponse<Record>> search(@RequestBody RoadEventRequest request) {
		Integer size = request.getDisplayLength();
		
		Integer pageNo = divide(request.getDisplayStart(),size);
		
		if (size == null || size == 0) {
			size = 10;
		}
		if(pageNo ==0) {
			pageNo =1;
		}
		 

		PageRequest pageable = new PageRequest(pageNo, size);
		 
		final String type = request.getRoadtype();
		String roadType=null;
		switch(type){
			case "0":
				//全部都要顯示
//				roadType="\\w";
				break;
			case "1":
				roadType="正常";
				//只顯示正常
				break;				
			case "2":
			//2018.01.08福哥說:路況正常時，name是空值; 路況不正常時，name就有值。下面的正則表示式是排除"正常"的字串
				roadType="^((?!正常).)*$";
				break;
		}
		
		Page<Record> data = service.find(roadType, request.getDes(), request.getStartDate(),
				request.getEndDate(), pageable);
		DataTableResponse<Record> converted = convert(data);
		converted.setsEcho(request.getEcho());

		/***
		 * client cache
		 */
		final CacheControl cc = CacheControl.maxAge(5l, TimeUnit.MINUTES);

		final ResponseEntity<DataTableResponse<Record>> result = ResponseEntity.ok().
				cacheControl(cc).
				header(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "86400")
				.header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST").body(converted);

		return result;
	}
}
