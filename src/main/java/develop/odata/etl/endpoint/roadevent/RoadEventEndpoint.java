package develop.odata.etl.endpoint.roadevent;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
	 * 快取
	 */
	private LoadingCache<String, Record[]> cacheData = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterAccess(5, TimeUnit.MINUTES).build(new CacheLoader<String, Record[]>() {
				@Override
				public Record[] load(String searchData) throws Exception {
					List<Record> result = service.findToday();

					return result.toArray(new Record[] {});
				}
			});

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<Record[]> listdata() {
		Record[] data = null;
		try {
			data = cacheData.get(ALL);
		} catch (ExecutionException e) {
			LOGGER.warn("An exception occurred while " + "fetching place details!", e.getCause());
			return null;
		}

		final ResponseEntity<Record[]> result = new ResponseEntity<Record[]>(data, HttpStatus.OK);

		return result;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value = "/search")
	public Slice<Record> search(@RequestParam(value = "rt", defaultValue = "", required = false) String roadtype,
			@RequestParam(value = "des", defaultValue = "", required = false) String des,
			@RequestParam(value = "start_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "end_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@PageableDefault(size = 10, page = 0, sort = {
					"happentime" }, direction = Direction.DESC) Pageable pageable) {
		Slice<Record> result = service.find(roadtype, des, startDate, endDate, pageable);

		return result;
	}
}
