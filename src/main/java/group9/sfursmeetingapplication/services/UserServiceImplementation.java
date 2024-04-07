/**
 * The UserServiceImplementation class provides an implementation of the UserService interface.
 * It includes methods to save a User and to verify a token.
 */
package group9.sfursmeetingapplication.services;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import group9.sfursmeetingapplication.models.Confirmation;
import group9.sfursmeetingapplication.models.ResetPassword;
import group9.sfursmeetingapplication.repositories.*;
import group9.sfursmeetingapplication.models.User;
import lombok.RequiredArgsConstructor;

@Service // This annotation is used to mark the class as a service provider
@RequiredArgsConstructor // This annotation is used to generate a constructor with required fields
public class UserServiceImplementation implements UserService {
    /**
     * The following constants are used to define the keys for the user data.
     */
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String TEAM = "team";
    private static final String TITLE = "title";

    @Autowired // This annotation is used to mark the field as autowired
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final ResetPasswordRepository resetPasswordRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);
        // Send an email to the user
        emailService.sendSimpleMailMessage(user.getFirstName(), user.getEmail(), confirmation.getToken());
        return user;
    }

    @Override
    public void sendPasswordEmail(String email) {

        User foundUser = userRepository.findByEmailIgnoreCase(email);
        if (foundUser != null) {
            if(resetPasswordRepository.findByUser(foundUser) != null) {
                resetPasswordRepository.delete(resetPasswordRepository.findByUser(foundUser));
            }
            ResetPassword reset = new ResetPassword(foundUser);
            resetPasswordRepository.save(reset);
            emailService.sendSimplePasswordMailMessage(foundUser.getFirstName(), foundUser.getEmail(), reset.getToken());
        }
        
    }


    

    /**
     * This method gets a user from the database.
     * 
     * @param email The email of the user to get.
     * @return The user from the database.
     */
    @Override
    public User getUser(String email) {
        User user = userRepository.findByEmailIgnoreCase(email);
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
        if (confirmation == null) {
            throw new IllegalArgumentException("Token does not exist. Try again.");
        }

        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        // Check user enabled status
        if (user.isEnabled()) {
            throw new IllegalArgumentException("User is already enabled.");
        }

        user.setEnabled(true);
        userRepository.save(user);
        confirmationRepository.delete(confirmation);
        return Boolean.TRUE;
    }

    @Override
    public Boolean verifyPasswordToken(String token) {
        ResetPassword confirmation = resetPasswordRepository.findByToken(token);
        if (confirmation == null) {
            throw new IllegalArgumentException("Token does not exist. Try Logging in.");
        }

        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        resetPasswordRepository.delete(confirmation);
        return Boolean.TRUE;
    }

    /**
     * This method gets a user from form data.
     * 
     * @param formData The form data.
     * @return The user from the form data.
     */
    @Override
    public User getUserFromFormData(Map<String, String> formData) {
        String email = formData.get(EMAIL);
        String password = formData.get(PASSWORD);
        User foundUser = userRepository.findByEmailIgnoreCase(email);

        if (foundUser == null) {
            throw new IllegalArgumentException("User does not exist. Please Register.");
        }
        if (!passwordEncoder.matches(password, foundUser.getPassword())) {
            foundUser = null;
            throw new IllegalArgumentException("Password is incorrect. Please try again.");
        }
        if (!foundUser.isEnabled()) {
            throw new IllegalArgumentException("Account is not enabled. Please verify your email.");
        }
        return foundUser;
    }

    /**
     * This method resends a confirmation email.
     * 
     * @param user The user to resend the confirmation email to.
     * @return The user that the confirmation email was resent to.
     */
    @Override
    public void resendConfirmation(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        User foundUser = userRepository.findByEmailIgnoreCase(email);
        if (foundUser == null) {
            throw new IllegalArgumentException("User does not exist. Please Register.");
        }
        if (!passwordEncoder.matches(password, foundUser.getPassword())) {
            throw new IllegalArgumentException("Password is incorrect. Please try again.");
        }
        Confirmation confirmation = confirmationRepository.findByUser(foundUser);
        if (confirmation == null) {
            throw new IllegalArgumentException("Confirmation does not exist.");
        }
        emailService.sendSimpleMailMessage(foundUser.getFirstName(), foundUser.getEmail(), confirmation.getToken());
    }

    /**
     * 
     * This method updates a user's profile.
     * 
     * @param userProfile The user profile to update.
     * @return True if the user profile was updated, false otherwise.
     */
    @Override
    public Boolean updateUserProfile(Map<String, String> userProfile) {
        if (!userProfile.containsKey(EMAIL) || !userProfile.containsKey(FIRST_NAME) ||
                !userProfile.containsKey(LAST_NAME) || !userProfile.containsKey(TEAM) ||
                !userProfile.containsKey(TITLE)) {
            throw new IllegalArgumentException("User profile is missing necessary information.");
        }
        String email = userProfile.get(EMAIL);
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found in Database.");
        }
        user.setFirstName(userProfile.get(FIRST_NAME));
        user.setLastName(userProfile.get(LAST_NAME));
        user.setTeam(userProfile.get(TEAM));
        user.setTitle(userProfile.get(TITLE));

        // Save the updated user profile
        userRepository.save(user);

        // Check if the user was saved successfully
        User savedUser = userRepository.findByEmailIgnoreCase(email);
        if (savedUser != null && savedUser.getFirstName().equals(userProfile.get(FIRST_NAME)) &&
                savedUser.getLastName().equals(userProfile.get(LAST_NAME)) &&
                savedUser.getTeam().equals(userProfile.get(TEAM)) &&
                savedUser.getTitle().equals(userProfile.get(TITLE))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method gets a user by their user uid.
     * @param uid The user uid.
     * @return The user with the given user uid.
     */
    @Override
    public User getUserById(Long uid) {
        User user = userRepository.findByUid(uid);
        if (user == null) {
            throw new IllegalArgumentException("User not found in Database.");
        }
        return user;
    }
}
