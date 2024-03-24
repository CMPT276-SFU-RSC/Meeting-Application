/**
 * The UserServiceImplementation class provides an implementation of the UserService interface.
 * It includes methods to save a User and to verify a token.
 */
package group9.sfursmeetingapplication.services;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import group9.sfursmeetingapplication.models.Confirmation;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.ConfirmationRepository;
import group9.sfursmeetingapplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service // This annotation is used to mark the class as a service provider
@RequiredArgsConstructor // This annotation is used to generate a constructor with required fields
public class UserServiceImplementation implements UserService {
    @Autowired // This annotation is used to mark the field as autowired
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

    /**
     * This method saves a user to the database.
     * 
     * @param user The user to be saved.
     * @return The user that was saved.
     * @throws IllegalArgumentException If the email already exists.
     */
    @Override
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setEnabled(false);
        userRepository.save(user);
        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);
        // Send an email to the user
        emailService.sendSimpleMailMessage(user.getFirstName(), user.getEmail(), confirmation.getToken());
        return user;
    }

    /**
     * This method verifies a token.
     * 
     * @param token The token to be verified.
     * @return True if the token is verified, false otherwise.
     */
    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        confirmationRepository.delete(confirmation);
        return Boolean.TRUE;
    }

    /**
     * This method gets a user from form data.
     * @param formData The form data.
     * @return The user from the form data.
     */
    @Override
    public User getUserFromFormData(Map<String, String> formData) {
        String email = formData.get("email");
        String password = formData.get("password");
        User foundUser = userRepository.findByEmailIgnoreCaseAndPassword(email, password);
        if (foundUser == null) {
            throw new IllegalArgumentException("Email or password is incorrect. Please try again.");
        }
        if (foundUser.isEnabled() == false) {
            throw new IllegalArgumentException("Please verify your email first.");
        }
        return foundUser;
    }

    /**
     * This method resends a confirmation email.
     * @param user The user to resend the confirmation email to.
     * @return The user that the confirmation email was resent to.
     */
    @Override
    public void resendConfirmation(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        User foundUser = userRepository.findByEmailIgnoreCaseAndPassword(email, password);
        if (foundUser == null) {
            throw new IllegalArgumentException("Email or password does not match or exist.");
        }

        Confirmation confirmation = confirmationRepository.findByUser(foundUser);
        if (confirmation == null) {
            throw new IllegalArgumentException("Confirmation does not exist.");
        }
        emailService.sendSimpleMailMessage(foundUser.getFirstName(), foundUser.getEmail(), confirmation.getToken());
    }
}
