package group9.sfursmeetingapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import group9.sfursmeetingapplication.models.Invited;
import group9.sfursmeetingapplication.models.Medium;
import group9.sfursmeetingapplication.models.Poll;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.InvitedRepository;
import group9.sfursmeetingapplication.repositories.MediumRepository;
import group9.sfursmeetingapplication.repositories.PollRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Map;


@Controller
public class PollController {

    @Autowired
    private PollRepository pollRepo;

    @Autowired
    private MediumRepository mediumRepo;

    @Autowired
    private InvitedRepository invitedRepo;

    @GetMapping(value = "/dashboard")
    public String getAllStudents(Model model, HttpServletRequest request,
    HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        System.out.println("User: " + user);
        if (user == null){
            //not logged in, redirect
            return "redirect:/login";
        } else {
            //generate dashboard
            //get from DB
            //get all polls this user has been invited to
            //  could in the future move results the user has answered
            //  List<Poll> polls = pollRepo.findByUID(user.uid);
            long uid = user.getUid();
            List<Poll> polls = pollRepo.findByUID(uid);
            model.addAttribute("polls", polls);
            model.addAttribute("user", user);
            return "users/dashboard";
        }
    }


    @GetMapping("/pollcreate")
    public String poll(Model model, HttpServletRequest request,
    HttpSession session) {
        User user = (User) session.getAttribute("session_user");

        
        if (user == null){
            //not logged in, redirect
            return "redirect:/login";
        } else {
            return "users/pollcreate";
        }
    }

    @PostMapping("/create-poll")
    public String createPoll(@RequestParam Map<String, String> pollData, HttpSession session) throws ParseException {
        User user = (User) session.getAttribute("session_user");
        if (user == null){
            //not logged in, redirect
            return "redirect:/login";
        } else {
            //add poll

            String title = pollData.get("title");
            String description = pollData.get("description");
            String startDateString = pollData.get("startDate");
            String startTimeString = pollData.get("startTime");
            String endDateString = pollData.get("endDate");
            String endTimeString = pollData.get("endTime");
            String expiraryDateString = pollData.get("expirary");

            // Parse dates
            java.time.Instant startDate = java.time.Instant.parse(startDateString+"T"+startTimeString + ":00.00Z");
            java.time.Instant endDate = java.time.Instant.parse(endDateString+"T"+endTimeString + ":00.00Z");
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

            
            //create and save mediums
            int i = 0;
            while (true){
                try {
                    //get json medium
                    String mediumText = pollData.get("m" + (Integer.toString(i)));
                    Boolean online = false;
                    if (mediumText.startsWith("(R) ")){
                        //(R) signals online
                        mediumText = mediumText.substring(4);
                        online = true;
                    }
                    //add to database
                    Medium medium = new Medium();
                    medium.setPid(newPoll.getPid());
                    medium.setRemote(online);
                    medium.setName(mediumText);
                    mediumRepo.save(medium);
                    i++;
                }
                catch(Exception e){
                    break;
                }
            }
            //create and save invited list
            i = 0;
            while (true){
                try {
                    // getting json users
                    String uid = pollData.get("u" + (Integer.toString(i)));
                    int end = uid.indexOf(')');
                    uid = uid.substring(1, end);
                    
                    //add to database
                    Invited invited = new Invited();
                    invited.setPid(newPoll.getPid());
                    invited.setUid(Integer.parseInt(uid));
                    invitedRepo.save(invited);
                    i++;
                }
                catch(Exception e){
                    break;
                }
            }
            
            return "redirect:/dashboard"; 
        }



    }

    @GetMapping("/getPolls/{pid}")
    public String displayEvents(@PathVariable int pid, Model model)  {
        List<Poll> polls = pollRepo.findBypid(pid);
        List<Medium> mediums = mediumRepo.findBypid(pid);
        if(polls.isEmpty()) {
            return "";
        }
        Poll poll = polls.get(0);
        model.addAttribute("poll", poll);
        model.addAttribute("mediums", mediums);
        return "users/showEvents";
    }
}
