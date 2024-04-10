
package group9.sfursmeetingapplication.repositoryTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testFindAll() {

        List<User> users = userRepository.findAll();
        assertNotNull(users);
    }

    @Test
    public void testFindByEmailIgnoreCase() {
        // Create a test user
        long generatedLong = 25;
        User userTest = new User(generatedLong, "organizer@yahoo.com", "password", "Harry", "Potter", "Robotics Team", "President", true, true);
    
        // Save the test user to the repository
        userRepository.save(userTest);

        User foundUser = userRepository.findByEmailIgnoreCase("organizer@yahoo.com");

        assertNotNull(foundUser);
        assertEquals(userTest.getEmail(), foundUser.getEmail());
    }

    @Test
    public void testExistsByEmail() {
        // Create a test user
        // Save the test user to the repository
        long generatedLong = 25;
        User userTest = new User(generatedLong, "organizer@yahoo.com", "password", "Harry", "Potter", "Robotics Team", "President", true, true);
    
        userRepository.save(userTest);

 
        boolean userExists = userRepository.existsByEmail("organizer@yahoo.com");

        assertTrue(userExists);
    }

    @Test
    public void testFindBySearch() {
        // Create test users
        long generatedLong = 25;
        User user1 = new User(generatedLong, "john@example.com", "password", "John", "Doe", "Robotics Team", "President", true, true);
        User user2 = new User(generatedLong, "jane@example.com", "password", "Jane", "Doe", "Robotics Team", "President", true, true);
        User user3 = new User(generatedLong, "bob@example.com", "password", "Bob", "Smith", "Robotics Team", "President", true, true);
        
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        List<User> foundUsers = userRepository.findBySearch("John");

        assertEquals(1, foundUsers.size());
        assertEquals("John", foundUsers.get(0).getFirstName());
    }

}
