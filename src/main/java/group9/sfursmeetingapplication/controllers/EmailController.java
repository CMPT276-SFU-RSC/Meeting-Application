package group9.sfursmeetingapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmailController {
    private final UserService userService;

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
            response.setStatus(401);
            return "redirect:/resendConfirmation";
        }
    }
    
    /**
     * Handles a GET request to confirm a user's account.
     * 
     * @param token The token to verify the user's account.
     * @return The view for the user.
     */
    @GetMapping("/email/verified") // Spring annotation to map HTTP GET requests onto specific handler methods
    public String confirmUserAccount(@RequestParam("token") String token) {
        if (token == null || token.isEmpty()) {
            return "redirect:/email/resendConfirmation";
        }
        userService.verifyToken(token);
        return "emails/emailConfirmed";
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
}
