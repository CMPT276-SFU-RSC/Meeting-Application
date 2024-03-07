/**
 * The ConfirmationRepository interface extends JpaRepository to provide an abstraction of data access for the Confirmation entity.
 * It provides methods to perform common database operations such as save, delete, and find on Confirmation entities.
 * It also includes a custom method to find a Confirmation by token.
 */
package group9.sfursmeetingapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import group9.sfursmeetingapplication.models.Confirmation;

@Repository // Spring annotation to indicate that the class is a repository.
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {

    /**
     * Finds a Confirmation by token.
     * 
     * @param token The token of the Confirmation to find.
     * @return The Confirmation with the given token.
     */
    Confirmation findByToken(String token);

}
