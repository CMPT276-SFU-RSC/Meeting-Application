/**
 * The HttpResponse class represents a standard HTTP response.
 * It includes a timestamp, status code, HTTP status, message, developer message, path, request method, and data.
 * DEPRICATED: This class is no longer used in the application remove deprication comment if used.
 */
package group9.sfursmeetingapplication.models;

import java.util.Map;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data // Lombok annotation to generate the getters and setters
@SuperBuilder // Lombok annotation to generate the builder pattern
@JsonInclude(JsonInclude.Include.NON_DEFAULT) // Jackson annotation to include non-default values
public class HttpResponse {
    protected String timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String message;
    protected String DeveloperMessage;
    protected String path;
    protected String reuqestMethod;
    protected Map<?, ?> data;
}
