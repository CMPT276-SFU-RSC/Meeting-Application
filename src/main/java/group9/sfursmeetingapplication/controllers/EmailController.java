package group9.sfursmeetingapplication.controllers;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import group9.sfursmeetingapplication.models.ResetPassword;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.*;
import group9.sfursmeetingapplication.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmailController {
    private final UserService userService;
    private final ResetPasswordRepository resetPasswordRepo; 
    private final UserRepository userRepo; 

        /**
     * Handles a POST request to resend a confirmation email.
     * 
     * @param user               The user to resend the confirmation email to.
     * @param response           The HTTP response.
     * @param redirectAttributes The redirect attributes.
     * @return The view for the user.
     */
    @PostMapping("/email/resend")
    public String resendConfirmation(@ModelAttribute User user, HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        try {
            userService.resendConfirmation(user);
            response.setStatus(201);
            return "universals/success";
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            response.setStatus(400);
            return "redirect:/email/resendConfirmation";
        }
    }

    @PostMapping("/email/confirmPassword/{email}")
    public String resendPasswordConfirmation(@PathVariable String email, @RequestParam Map<String, String> newValue, HttpServletResponse response) {
        User user = userRepo.findByEmailIgnoreCase(email);
        user.setPassword(newValue.get("password1Reset"));
        return "universals/success";
    }

   @PostMapping("/email/resetPassword")
    public String resetPassword(@RequestParam Map<String, String> newModel, HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        try {
            String email = newModel.get("emailReset");
            userService.sendPasswordEmail(email);
            response.setStatus(201);
            return "universals/success";
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            response.setStatus(400);
            return "redirect:/email/forgotPassword";
        }
    } 
    
    
    /**
     * Handles a GET request to confirm a user's account.
     * 
     * @param token The token to verify the user's account.
     * @return The view for the user.
     */
    @GetMapping("/email/verified") // Spring annotation to map HTTP GET requests onto specific handler methods
    public String confirmUserAccount(@RequestParam("token") String token, HttpServletResponse response,
    RedirectAttributes redirectAttributes) {
        // Check if the token is null or empty
        if (token == null || token.isEmpty()) {
            return "redirect:/email/resendConfirmation";
        }

        // if there is a token, verify the token
        try {
            userService.verifyToken(token);
            response.setStatus(200);
            return "emails/emailConfirmed";
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            response.setStatus(400);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/email/verifiedForgotPassword") // Spring annotation to map HTTP GET requests onto specific handler methods
    public String confirmUserForgotPassword(Model model, @RequestParam("token") String token, HttpServletResponse response,
    RedirectAttributes redirectAttributes) {
        // Check if the token is null or empty
        if (token == null || token.isEmpty()) {
            return "redirect:/email/resetPassword";
        }

        // if there is a token, verify the token
        try {
            ResetPassword reset = resetPasswordRepo.findByToken(token);
            User user = reset.getUser();
            String email = user.getEmail();
            userService.verifyPasswordToken(token);
            response.setStatus(200);
            model.addAttribute("email", email);
            return "emails/resetPassword";
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            response.setStatus(400);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "users/login";
        }
    }

    /**
     * Handles a GET request to resend a confirmation email.
     * 
     * @return The view for the user.
     */
    @GetMapping("/email/resendConfirmation")
    public String resendConfirmation() {
        return "emails/resendConfirmation";
    }

    @GetMapping("/email/forgotPassword")
    public String resetPasswordPage() {
        return "users/forgotPassword";
    }
}
