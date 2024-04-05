package group9.sfursmeetingapplication.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import group9.sfursmeetingapplication.dto.InvitedDTO;
import group9.sfursmeetingapplication.dto.PollDTO;
import group9.sfursmeetingapplication.models.Invited;
import group9.sfursmeetingapplication.models.Medium;
import group9.sfursmeetingapplication.models.Poll;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.InvitedRepository;
import group9.sfursmeetingapplication.repositories.MediumRepository;
import group9.sfursmeetingapplication.repositories.PollRepository;
import group9.sfursmeetingapplication.services.PollService;
import group9.sfursmeetingapplication.services.UserService;
import group9.sfursmeetingapplication.services.InvitedService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PollController {
    private final UserService userService; // This is a final variable, so it must be initialized in the constructor
    private final PollService pollService; // This is a final variable, so it must be initialized in the constructor
    private final InvitedService invitedService; // This is a final variable, so it must be initialized in the
                                                 // constructor
    @Autowired
    private PollRepository pollRepo;
    // private UserRepository userRepo1;

    @Autowired
    private MediumRepository mediumRepo;

    @Autowired
    private InvitedRepository invitedRepo;

    @GetMapping(value = "/dashboard")
    public String getAllStudents(Model model, HttpServletRequest request,
            HttpSession session) {
        // Check if the user is logged in
        session = request.getSession(false);
        if (session == null) {
            System.out.println("Redirecting because there's no session");
            // If the user is not logged in, redirect them to the login page
            return "redirect:/login";
        }

        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            System.out.println("Redirecting because there's no user ID in the session");
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Redirecting because the user doesn't exist");
            // If the user doesn't exist, end the session and redirect the user to the login
            // page
            session.invalidate();
            return "redirect:/login";
        } // End of session check

        // generate dashboard
        // get from DB
        // get all polls this user has been invited to
        // could in the future move results the user has answered
        // List<Poll> polls = pollRepo.findByUID(user.uid);
        List<Poll> polls = pollRepo.findByUID(user.getUid());
        List<Poll> polls1 = pollRepo.find();
        // Gets a list of all the polls the user has created.
        List<Poll> createdPolls = pollRepo.findByCreator_id(user.getUid());
        model.addAttribute("polls1", polls1);
        model.addAttribute("polls", polls);
        model.addAttribute("user", user);
        model.addAttribute("createdPolls", createdPolls);

        return "users/dashboard";
    }

    @GetMapping("/pollcreate")
    public String poll(Model model, HttpServletRequest request,
            HttpSession session) {
        session = request.getSession(false);
        if (session == null) {
            System.out.println("Redirecting because there's no session");
            // If the user is not logged in, redirect them to the login page
            return "redirect:/login";
        }

        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            System.out.println("Redirecting because there's no user ID in the session");
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Redirecting because the user doesn't exist");
            // If the user doesn't exist, end the session and redirect the user to the login
            // page
            session.invalidate();
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "users/pollcreate";
    }

    @PostMapping("/create-poll")
    public String createPoll(@RequestParam Map<String, String> pollData, HttpSession session,
            HttpServletRequest request) throws ParseException {
        session = request.getSession(false);
        if (session == null) {
            System.out.println("Redirecting because there's no session");
            // If the user is not logged in, redirect them to the login page
            return "redirect:/login";
        }

        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            System.out.println("Redirecting because there's no user ID in the session");
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Redirecting because the user doesn't exist");
            // If the user doesn't exist, end the session and redirect the user to the login
            // page
            session.invalidate();
            return "redirect:/login";
        }
        // add poll

        String title = pollData.get("title");
        String description = pollData.get("description");
        String startDateString = pollData.get("startDate");
        String startTimeString = pollData.get("startTime");
        String endDateString = pollData.get("endDate");
        String endTimeString = pollData.get("endTime");
        String expiraryDateString = pollData.get("expirary");

        // Parse dates
        java.time.Instant startDate = java.time.Instant.parse(startDateString + "T" + startTimeString + ":00.00Z");
        java.time.Instant endDate = java.time.Instant.parse(endDateString + "T" + endTimeString + ":00.00Z");
        java.time.Instant expiraryDate = java.time.Instant.parse(expiraryDateString + "T23:59:00.00Z");

        // Create and save Poll object
        Poll newPoll = new Poll();
        newPoll.setTitle(title);
        newPoll.setDescription(description);
        newPoll.setStartDate(startDate);
        newPoll.setEndDate(endDate);
        newPoll.setExpirary(expiraryDate);
        newPoll.setCreator_id(user.getUid());

        pollRepo.save(newPoll);

        // create and save mediums
        int i = 0;
        while (true) {
            try {
                // get json medium
                String mediumText = pollData.get("m" + (Integer.toString(i)));
                Boolean online = false;
                if (mediumText.startsWith("(R) ")) {
                    // (R) signals online
                    mediumText = mediumText.substring(4);
                    online = true;
                }
                // add to database
                Medium medium = new Medium();
                medium.setPid(newPoll.getPid());
                medium.setRemote(online);
                medium.setName(mediumText);
                mediumRepo.save(medium);
                i++;
            } catch (Exception e) {
                break;
            }
        }
        // create and save invited list
        i = 0;
        while (true) {
            try {
                // getting json users
                String uid = pollData.get("u" + (Integer.toString(i)));
                int end = uid.indexOf(')');
                uid = uid.substring(1, end);

                // add to database
                Invited invited = new Invited();
                invited.setPid(newPoll.getPid());
                invited.setUid(Integer.parseInt(uid));
                invitedRepo.save(invited);
                i++;
            } catch (Exception e) {
                break;
            }
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/getPolls/{pid}")
    public String displayEvents(@PathVariable int pid, Model model, HttpSession session) {
        List<Poll> polls = pollRepo.findBypid(pid);
        List<Medium> mediums = mediumRepo.findBypid(pid);
        if (polls.isEmpty()) {
            return "";
        }
        Poll poll = polls.get(0);
        model.addAttribute("poll", poll);
        model.addAttribute("mediums", mediums);

        // Check if the user is logged in
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            System.out.println("Redirecting because there's no user ID in the session");
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Redirecting because the user doesn't exist");
            // If the user doesn't exist, end the session and redirect the user to the login
            // page
            session.invalidate();
            return "redirect:/login";
        } // End of session check
        model.addAttribute("user", user);
        return "users/showEvents";
    }

    /**
     * Respond to a poll
     * 
     * @param pid
     * @param model
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/polls/respond/{pid}")
    public String respondPoll(@PathVariable int pid, Model model, HttpSession session,
            HttpServletRequest request) {
        session = request.getSession(false);
        if (session == null) {
            System.out.println("Redirecting because there's no session");
            // If the user is not logged in, redirect them to the login page
            return "redirect:/login";
        }

        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            System.out.println("Redirecting because there's no user ID in the session");
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Redirecting because the user doesn't exist");
            // If the user doesn't exist, end the session and redirect the user to the login
            // page
            session.invalidate();
            return "redirect:/login";
        }
        
        try {
            // Get the poll
            Poll poll = pollRepo.findByPid(pid);
            // Get the user who created the poll
            User creator = userService.getUserById(poll.getCreator_id());
     
            String fullName = creator.getFirstName() + " " + creator.getLastName();
            // Create a Poll DTO.
            PollDTO pollDTO = pollService.createPollFromDTO(poll, fullName);
    
            // Get the list of users that are invited to the poll.
            // invitedService.createListOfInvitedFromDTO(invitedRepo.findByPid(pid));
            List<Object[]> queryResults = invitedRepo.findByPid(pid);
            List<InvitedDTO> invitedDTOs = invitedService.createListOfInvitedFromDTO(queryResults);
    
            model.addAttribute("invited", invitedDTOs);
            model.addAttribute("poll", pollDTO);
            model.addAttribute("user", user);
    
            return "polls/respond";
        } catch (Exception e) {
            return "redirect:/dashboard";
        }
    }
}
