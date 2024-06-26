package group9.sfursmeetingapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import group9.sfursmeetingapplication.models.ResetPassword;
import group9.sfursmeetingapplication.models.User;

@Repository // Spring annotation to indicate that the class is a repository.
public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long> {

    /**
     * Finds a Confirmation by token.
     * 
     * @param token The token of the Confirmation to find.
     * @return The Confirmation with the given token.
     */
    ResetPassword findByToken(String token);

    /**
     * Finds a Confirmation by uid.
     * 
     * @param uid
     * @return The Confirmation with the given uid.
     */
    ResetPassword findByUser(User user);

}
