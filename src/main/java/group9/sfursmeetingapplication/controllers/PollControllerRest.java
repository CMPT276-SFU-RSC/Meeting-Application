/**
 * This class is a REST controller that handles the HTTP requests for the Polls.
 */
package group9.sfursmeetingapplication.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import group9.sfursmeetingapplication.repositories.PollRepository;
import group9.sfursmeetingapplication.repositories.ResponseRepository;
import group9.sfursmeetingapplication.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import group9.sfursmeetingapplication.models.*;
import group9.sfursmeetingapplication.services.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Lombok annotation to generate the required constructor
@RestController // This annotation is used to mark the class as a REST controller.
public class PollControllerRest {
    private final ResponseService responseService;
    private final UserService userService; 

    @Autowired
    private ResponseRepository responseRepo;

    @Autowired
    private PollRepository pollRepo; 

    @Autowired
    private UserRepository userRepo; 

    /**
     * This method is used to respond to a poll.
     * 
     * @param responses The list of responses.
     * @param request   The HTTP request.
     * @param response  The HTTP response.
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

            List<Integer> InvitedUsersList = pollRepo.findInvitedByPID(responses.get(0).getPid());
            List<Integer> RespondedUsersList = pollRepo.findResponseByPID(responses.get(0).getPid());

            Double respondedUsersSize = Double.valueOf(RespondedUsersList.size());
            Double invitedUsersSize = Double.valueOf(InvitedUsersList.size());

            Double preResponse = (respondedUsersSize - 1.0)/invitedUsersSize;
            Double withResponse = respondedUsersSize/invitedUsersSize;

            if(preResponse < 0.80 && withResponse >= 0.80) {

                Poll poll = pollRepo.findByPid(responses.get(0).getPid());
                List<User> usersList = userRepo.findUserByUID(poll.getCreator_id());
                User foundUser = usersList.get(0);
                userService.sendPollReadyEmail(foundUser);
            }
            return new ResponseEntity<>(Collections.singletonMap("message", "Responses saved successfully"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(Collections.singletonMap("error", "Error saving responses"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is used to update the response to a poll.
     * 
     * @param responses The list of responses.
     * @param request   The HTTP request.
     * @param response  The HTTP response.
     * @return ResponseEntity<String> The response entity.
     */
    @PostMapping("/poll/update")
    public ResponseEntity<Map<String, String>> updateResponseToPoll(@RequestBody List<Response> responses,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("Updating Poll Responses....");
            for (Response oneResponse : responses) {
                responseService.updateUserResponse(oneResponse);
            }
            return new ResponseEntity<>(Collections.singletonMap("message", "Responses updated successfully"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(Collections.singletonMap("error", "Error saving responses"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/poll/response/{uid}/{mid}")
    public List<Response> getUserResponse(@PathVariable long uid, @PathVariable long mid) {
        List<Response> res = responseRepo.findByMidAndUid(Math.toIntExact(mid), Math.toIntExact(uid));
        return res;
    }

    @GetMapping("/poll/response/{mid}")
    public List<Response> getMediumResponse(@PathVariable long mid) {
        List<Response> res = responseRepo.findByMid(Math.toIntExact(mid));
        return res;
    }
}
