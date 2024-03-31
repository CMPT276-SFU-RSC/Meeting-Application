/**
 * UserController is a Spring Boot controller that handles HTTP requests related to users.
 * It uses UserService to perform operations related to users.
 */
package group9.sfursmeetingapplication.controllers;

import java.util.Map;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import group9.sfursmeetingapplication.repositories.PollRepository;
import group9.sfursmeetingapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import group9.sfursmeetingapplication.models.Poll;

@Controller // Spring annotation to mark the class as a controller
@RequiredArgsConstructor // Lombok annotation to generate the required constructor
public class UserController {
    private final UserService userService;
    @Autowired
    private PollRepository pollRepo;
    @Autowired
    private UserRepository userRepo1;

    /**
     * Handles a POST request to login a user.
     * 
     * @param formData           The form data for the user.
     * @param model              The model for the user.
     * @param request            The HTTP request.
     * @param session            The HTTP session.
     * @param redirectAttributes The redirect attributes.
     * @param response           The HTTP response.
     * @return The view for the user.
     */
    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> formData, Model model,
            HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes,
            HttpServletResponse response) {
        try {
            System.out.println("Finding User...");
            User user = userService.getUserFromFormData(formData);
            System.out.println("Controller User: " + user); // delete later
            request.getSession().setAttribute("user_id", user.getUid());
            model.addAttribute("user", user);

            response.setStatus(201);
            return "redirect:/dashboard";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            response.setStatus(401);
            return "redirect:/login";
        }
    }

    /**
     * Handles a POST request to create a new user.
     * It uses UserService to save the user and sets the HTTP status to 201.
     * 
     * @param user     The user to create.
     * @param response The HTTP response.
     * @return The view for the user.
     */
    @PostMapping("/register") // Spring annotation to map HTTP POST requests onto specific handler methods
    public String createUser(@ModelAttribute User user, HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Creating User...");
            userService.saveUser(user);
            response.setStatus(201);
            return "universals/success";
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            response.setStatus(401);
            return "redirect:/registration";
        }
    }

    /**
     * Handles a POST request to save a user's profile.
     * 
     * @param userDetails        The user's details.
     * @param redirectAttributes The redirect attributes.
     * @param response           The HTTP response.
     * @param session            The HTTP session.
     * @return The view for the user.
     */
    @PostMapping("/users/save")
    public String saveUser(@RequestParam Map<String, String> userDetails,
            RedirectAttributes redirectAttributes, HttpServletResponse response,
            HttpSession session) {
        try {
            System.out.println("Updating user...");
            Boolean updated = userService.updateUserProfile(userDetails);
            if (updated) {
                response.setStatus(201);
                return "universals/success";
            } else {
                throw new IllegalArgumentException("Something went wrong. Please try again.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            response.setStatus(401);
            return "redirect:/users/profile";
        }
    }

    /**
     * Handles a GET request to redirect to the login page.
     */
    @GetMapping("/")
    public String returnToLogin(HttpServletRequest request, HttpSession session) {
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
        return "redirect:/dashboard";
    }

    /**
     * Handles a GET request to register a new user.
     * 
     * @return The view for the user registration.
     */
    @GetMapping("/registration")
    public String registerUser() {
        return "users/registration";
    }

    /**
     * Handles a GET request to login a user.
     */
    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session) {
        Long user = (Long) session.getAttribute("user_id");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
            return "users/dashboard";
        }
    }

    /**
     * Handles a GET request to redirect to the dashboard.
     * 
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/users/profile")
    public String profile(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
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

        model.addAttribute("user", user);
        return "users/profile";
    }

    /**
     * Handles a GET request to redirect to the dashboard.
     * 
     * @param session The HTTP session.
     * @return The view for the user.
     */
    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/delete")
    public String deletepage(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        return "users/delete";
    }

    @PostMapping("/users/delete")
    public String deleteuser(@RequestParam Map<String, String> poll, HttpServletResponse response) {
        // TODO: process POST request
        Integer snumberr = Integer.parseInt(poll.get("snumber"));
        List<Poll> usersToDelete = pollRepo.findname(snumberr);
        for (Poll user : usersToDelete) {
            pollRepo.delete(user);
        }
        response.setStatus(200);
        return "redirect:/dashboard";

    }

    @GetMapping("/userdisplay")
    public String userpage(Model model, HttpServletRequest request, HttpSession session) {
        List<User> u1 = userRepo1.findall();
        model.addAttribute("u1", u1);
        return "users/display";
    }

    @GetMapping("/deleteuser")
    public String deleteuser1(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        return "users/userdelete";
    }

    @PostMapping("/users/d")
    public String deleteuser2(@RequestParam Map<String, String> poll, HttpServletResponse response) {
        // TODO: process POST request

        Integer snumberr = Integer.parseInt(poll.get("snumber"));
        List<User> usersToDelete1 = userRepo1.findname(snumberr);
        for (User user : usersToDelete1) {
            userRepo1.delete(user);
        }

        response.setStatus(200);
        return "redirect:/dashboard";
    }
}
