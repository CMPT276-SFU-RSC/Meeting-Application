package group9.sfursmeetingapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import group9.sfursmeetingapplication.models.Poll;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.PollRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PollController {

    @Autowired
    private PollRepository pollRepo;
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
}
