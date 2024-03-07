package group9.sfursmeetingapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import group9.sfursmeetingapplication.models.Poll;
import group9.sfursmeetingapplication.models.PollRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class PollController {

    @Autowired
    private PollRepository pollRepo;
    @GetMapping(value = "/dashboard")
    public String getAllStudents(Model model, HttpSession session){
        // User user = (User) session.getAttribute("user");
        // if (user == null){
        //     //not logged in, redirect
        //     return "login";
        // }
        // else {

            //generate dashboard

            //get from DB
            //get all polls this user has been invited to
            //  could in the future move results the user has answered
            List<Poll> polls = pollRepo.findByUID(user.uid);
            
            model.addAttribute("polls", polls);
            return "userDashboard";

            
        //}
    }
}
