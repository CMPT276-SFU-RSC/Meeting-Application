/**
 * This class is a REST controller that handles the HTTP requests for the Polls.
 */
package group9.sfursmeetingapplication.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import group9.sfursmeetingapplication.models.Response;
import group9.sfursmeetingapplication.services.ResponseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Lombok annotation to generate the required constructor
@RestController // This annotation is used to mark the class as a REST controller.
public class PollControllerRest {
    private final ResponseService responseService;

    /**
     * This method is used to respond to a poll.
     * @param responses The list of responses.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @return ResponseEntity<String> The response entity.
     */
    @PostMapping("/poll/respond")
    public ResponseEntity<Map<String, String>> respondToPoll(@RequestBody List<Response> responses,
    HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("Saving Poll Responses....");
            for (Response oneResponse : responses) {
                responseService.saveUserResponse(oneResponse);
            }
            return new ResponseEntity<>(Collections.singletonMap("message", "Responses saved successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(Collections.singletonMap("error", "Error saving responses"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
