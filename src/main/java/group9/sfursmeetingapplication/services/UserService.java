/**
 * The UserService interface provides an abstraction for operations related to User entities.
 * It includes methods to save a User and to verify a token.
 */
package group9.sfursmeetingapplication.services;

import group9.sfursmeetingapplication.models.User;

public interface UserService {

    /**
     * Saves a User to the database.
     * @param user The User to save.
     * @return The saved User.
     */
    User saveUser(User user); // Possible to decouple from the database using DTOs.

    /**
     * Verifies a token.
     * @param token The token to verify.
     * @return True if the token is valid, false otherwise.
     */
    Boolean verifyToken(String token);
}
