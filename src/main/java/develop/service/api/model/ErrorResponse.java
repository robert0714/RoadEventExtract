package develop.service.api.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;


    private final Date timestamp;

    public ErrorResponse(final String message,  HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = new java.util.Date();
    }

 

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }
 
    public Date getTimestamp() {
        return timestamp;
    }
}
