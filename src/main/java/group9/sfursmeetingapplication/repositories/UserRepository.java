/**
 * UserRepository is an interface that extends JpaRepository to provide an abstraction of data access for the User entity.
 * It provides methods to perform common database operations such as save, delete, and find on User entities.
 * It also includes custom methods to find a User by email (ignoring case) and to check if a User exists by email.
 */
package group9.sfursmeetingapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import group9.sfursmeetingapplication.models.User;
import jakarta.transaction.Transactional;

@Repository // Spring annotation to indicate that the class is a repository.
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a User by email (ignoring case).
     * 
     * @param email The email of the User to find.
     * @return The User with the given email (ignoring case).
     */
    User findByEmailIgnoreCase(String email);

    /**
     * Checks if a User exists by email.
     * 
     * @param email The email of the User to check.
     * @return True if a User with the given email exists, false otherwise.
     */
    Boolean existsByEmail(String email);

    /**
     * Finds a User by email and password.
     * 
     * @param email    The email of the User to find.
     * @param password The password of the User to find.
     * @return The User with the given email and password.
     */
    User findByEmailIgnoreCaseAndPassword(String email, String password);

    List<User> findAll();

    /**
     * Finds a User by user uid.
     * 
     * @param uid The user uid.
     * @return User
     */
    User findByUid(Long uid);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findall();

    @Query(value = "SELECT * FROM users WHERE uid = ?1", nativeQuery = true)
    List<User> findUserByUID(Long uid);

    @Query(value = "SELECT * FROM users WHERE uid = ?1", nativeQuery = true)
    List<User> findname(Integer uid);

    @Query(value = "delete from users where uid =?1", nativeQuery = true)
    void deleteuser(Long uid);

    @Query(value = "SELECT uid FROM invited WHERE pid = ?1", nativeQuery = true)
    List<Integer> findusers1(Integer pid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users " +
            "WHERE users.uid = ?1 ;", nativeQuery = true)
    void deleteByUID(long uid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM confirmations " +
            "WHERE confirmations.uid = ?1 ;", nativeQuery = true)
    void deleteConfirmation(long uid);

    @Query(value = "SELECT users.* " +
            "FROM users " +
            "WHERE UPPER(users.first_name || ' ' || users.last_name) LIKE UPPER(?1 || '%');", nativeQuery = true)
    List<User> findBySearch(String firstName);

    @Query(value = "SELECT users.* " +
            "FROM users, invited, polls " +
            "WHERE polls.pid = ?1 AND users.uid = invited.uid AND polls.pid = invited.pid;", nativeQuery = true)
    List<User> findByPollPid(int pid);
}
