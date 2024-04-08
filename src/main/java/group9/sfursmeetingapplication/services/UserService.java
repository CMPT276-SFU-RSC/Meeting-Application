/**
 * The UserService interface provides an abstraction for operations related to User entities.
 * It includes methods to save a User and to verify a token.
 */
package group9.sfursmeetingapplication.services;

import java.util.Map;
import group9.sfursmeetingapplication.models.User;

public interface UserService {

    /**
     * Saves a User to the database.
     * 
     * @param user The User to save.
     */
    User saveUser(User user); // Possible to decouple from the database using DTOs.

    void sendPasswordEmail(String email); 

    void sendPollReadyEmail(User user);
    /**
     * Verifies a token.
     * 
     * @param token The token to verify.
     */
    Boolean verifyToken(String token);

    Boolean verifyPasswordToken(String token);

    /**
     * Gets a User from form data.
     * 
     * @param user The form data.
     */
    User getUserFromFormData(Map<String, String> user);

    /**
     * Resends a confirmation email.
     * 
     * @param user The user to resend the confirmation email to.
     */
    void resendConfirmation(User user);

    /**
     * Gets a User from form data.
     * 
     * @param user The form data.
     */
    Boolean updateUserProfile(Map<String, String> user);

    /**
     * Gets a user from the database.
     * 
     * @param user The form data.
     */
    User getUser(String email);

    /**
     * Gets a user from the database.
     * @param uid The user id.
     * @return User
     */
    User getUserById(Long uid);
}
