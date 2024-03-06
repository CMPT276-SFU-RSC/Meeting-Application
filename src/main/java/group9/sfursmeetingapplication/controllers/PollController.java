package group9.sfursmeetingapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import group9.sfursmeetingapplication.models.Poll;
import group9.sfursmeetingapplication.models.PollRepository;

@Controller
public class PollController {

    @Autowired
    private PollRepository pollRepo;
    @GetMapping(value = "/dashboard")
    public String getAllStudents(Model model){

        //get from DB
        //need to verify it has the right user, likely through session giving userID, and searching through the "invited" list.
        List<Poll> polls = pollRepo.findAll();
        
        model.addAttribute("polls", polls);
        
        return "userDashboard";
    }
}
