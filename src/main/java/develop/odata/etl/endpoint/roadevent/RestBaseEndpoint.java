package develop.odata.etl.endpoint.roadevent;
 

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import develop.service.api.model.ErrorResponse;
 

@ControllerAdvice
public class RestBaseEndpoint extends WebMvcConfigurerAdapter {
 
  

	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseBody
	ResponseEntity<ErrorResponse> handleBadRequest(HttpServletRequest req,
			HttpMessageConversionException ex) {
		// return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
		// .body(ex.getErrorResponse());
		
		Throwable rootException = ExceptionUtils.getRootCause(ex);
		ErrorResponse result = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);

	}

	
}