package group9.sfursmeetingapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import group9.sfursmeetingapplication.models.Poll;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.PollRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Controller
public class PollController {

    @Autowired
    private PollRepository pollRepo;
    //private UserRepository userRepo1;
  

    @GetMapping(value = "/dashboard")
    public String getAllStudents(Model model, HttpServletRequest request,
    HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // If the user is not logged in, redirect them to the login page
            return "redirect:/login";
        } else {
            //generate dashboard
            //get from DB
            //get all polls this user has been invited to
            //  could in the future move results the user has answered
            //  List<Poll> polls = pollRepo.findByUID(user.uid);
            long uid = user.getUid();
            List<Poll> polls = pollRepo.findByUID(uid);
            List<Poll> polls1 = pollRepo.find();
            
            
            model.addAttribute("polls1", polls1);
            model.addAttribute("polls", polls);
            model.addAttribute("user", user);
            return "users/dashboard";
        }
    }



    @PostMapping("/create-poll")
    public String createPoll(@RequestParam Map<String, String> pollData) throws ParseException {
        String title = pollData.get("title");
        String description = pollData.get("description");
        String startDateString = pollData.get("startDate");
        String endDateString = pollData.get("endDate");
        String expiraryDateString = pollData.get("expirary");

        // Parse dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(startDateString);
        Date endDate = dateFormat.parse(endDateString);
        Date expiraryDate = dateFormat.parse(expiraryDateString);

        // Create and save Poll object
        Poll newPoll = new Poll();
        newPoll.setTitle(title);
        newPoll.setDescription(description);
        newPoll.setStartDate(new java.sql.Date(startDate.getTime()));
        newPoll.setEndDate(new java.sql.Date(endDate.getTime()));
        newPoll.setExpirary(new java.sql.Date(expiraryDate.getTime()));

        pollRepo.save(newPoll);

        return "redirect:/dashboard"; 
    }

   
   
   
   

}
