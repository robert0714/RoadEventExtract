package develop.odata.etl.endpoint.roadevent;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import develop.odata.etl.domain.roadevent.Record;
import develop.odata.etl.service.roadevent.RoadEventService;

@RestController
@RequestMapping(value = "/roadEvent")
public class RoadEventEndpoint {
	
	@Autowired
	private RoadEventService service ;
	
	/** The logger. */
	private final Logger LOGGER = LoggerFactory.getLogger(RoadEventEndpoint.class);
	private static String ALL = "ALL";

	/***
	 * 快取
	 * */
	private LoadingCache<String, List<Record>> cacheData = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterAccess(5, TimeUnit.MINUTES).build(new CacheLoader<String, List<Record>>() {
				@Override
				public List<Record> load(String searchData) throws Exception {
					List<Record> result = service.findAll();

					return result;
				}
			});

	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE, value = "/")
	public ResponseEntity<List<Record>> listdata() {
		List<Record> data = null;
		try {
			data = cacheData.get(ALL);
		} catch (ExecutionException e) {
			LOGGER.warn("An exception occurred while " + "fetching place details!", e.getCause());
			return null;
		}

		final ResponseEntity<List<Record>> result = new ResponseEntity<List<Record>>(data, HttpStatus.OK);

		return result;
	}

}
