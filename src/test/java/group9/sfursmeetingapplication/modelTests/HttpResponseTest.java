package group9.sfursmeetingapplication.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.http.HttpStatus;
import org.springframework.boot.test.context.SpringBootTest;

import group9.sfursmeetingapplication.models.HttpResponse;

@SpringBootTest
public class HttpResponseTest {

    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        // Initialize a sample HttpResponse object before each test
        httpResponse = HttpResponse.builder()
                .timeStamp("2024-03-26 12:00:00")
                .statusCode(200)
                .status(HttpStatus.OK)
                .message("Success")
                .DeveloperMessage("No developer message")
                .path("/api/resource")
                .reuqestMethod("GET")
                .data(new HashMap<>())
                .build();
    }

    @Test
    @DisplayName("Test Getters")
    void testGetters() {
        assertNotNull(httpResponse.getTimeStamp());
        assertEquals(200, httpResponse.getStatusCode());
        assertEquals(HttpStatus.OK, httpResponse.getStatus());
        assertEquals("Success", httpResponse.getMessage());
        assertEquals("No developer message", httpResponse.getDeveloperMessage());
        assertEquals("/api/resource", httpResponse.getPath());
        assertEquals("GET", httpResponse.getReuqestMethod());
        assertNotNull(httpResponse.getData());
    }

    @Test
    @DisplayName("Test Builder Pattern")
    void testBuilderPattern() {
        Map<String, String> testData = new HashMap<>();
        testData.put("key", "value");

        HttpResponse newResponse = HttpResponse.builder()
                .timeStamp("2024-03-26 13:00:00")
                .statusCode(404)
                .status(HttpStatus.NOT_FOUND)
                .message("Not Found")
                .DeveloperMessage("Resource not found")
                .path("/api/missing")
                .reuqestMethod("POST")
                .data(testData)
                .build();

        assertNotNull(newResponse);
        assertEquals("2024-03-26 13:00:00", newResponse.getTimeStamp());
        assertEquals(404, newResponse.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, newResponse.getStatus());
        assertEquals("Not Found", newResponse.getMessage());
        assertEquals("Resource not found", newResponse.getDeveloperMessage());
        assertEquals("/api/missing", newResponse.getPath());
        assertEquals("POST", newResponse.getReuqestMethod());
        assertEquals(testData, newResponse.getData());
    }
}