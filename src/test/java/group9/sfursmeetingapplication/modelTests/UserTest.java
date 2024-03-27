package group9.sfursmeetingapplication.modelTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import group9.sfursmeetingapplication.controllers.UserController;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

@WebMvcTest(UserController.class)
public class UserTest {

    private User userEmpty; 
    private User userAll; 

    @BeforeEach
    public void setUp() {
        userEmpty = new User(); 
        long generatedLong = 25;
        userAll = new User(generatedLong, "organizer@yahoo.com", "password", "Harry", "Potter", "Robotics Team", "President", true, true);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testGetUid() {
        long newLong = userAll.getUid();
        assertEquals(newLong, 25);
    }

    @Test
    public void testSetUid() {
        long newLong = 20; 
        userAll.setUid(newLong);
        assertEquals(newLong, userAll.getUid());
    }

    @Test
    public void testGetEmail() {
        String newEmail = userAll.getEmail(); 
        assertEquals(newEmail, "organizer@yahoo.com");
    }

    @Test
    public void testSetEmail() {
        userAll.setEmail("hello@gmail.com"); 
        assertEquals("hello@gmail.com", userAll.getEmail());
    }

    @Test
    public void testGetFirstName() {
        String newFirstName = userAll.getFirstName();
        assertEquals(newFirstName, "Harry");
    }

    @Test
    public void testSetFirstName() {
        userAll.setFirstName("Bobby");
        assertEquals("Bobby", userAll.getFirstName());
    }
    

    @Test
    public void testGetLastName() {
        String newLastName = userAll.getLastName();
        assertEquals(newLastName, "Potter");
    }

    @Test
    public void testSetLastName() {
        userAll.setLastName("Chan");
        assertEquals("Chan", userAll.getLastName());
    }
    

    @Test
    public void testGetTeam() {
        String newTeam = userAll.getTeam();
        assertEquals(newTeam, "Robotics Team");
    }

    @Test
    public void testSetTeam() {
        userAll.setTeam("Software Team");
        assertEquals("Software Team", userAll.getTeam());
    }


    @Test
    public void testGetTitle() {
        String newTitle = userAll.getTitle();
        assertEquals(newTitle, "President");
    }

    @Test
    public void testSetTitle() {
        userAll.setTitle("Vice President");
        assertEquals("Vice President", userAll.getTitle());
    }

    @Test
    public void testGetIsOrganizer() {
        Boolean newIsOrganizer = userAll.isOrganizer(); 
        assertEquals(newIsOrganizer, true);
    }

    @Test
    public void testSetIsOrganizer() {
        userAll.setOrganizer(false);
        assertEquals(false, userAll.isOrganizer());
    }

    @Test
    public void testGetIsEnabled() {
        Boolean newIsEnabled = userAll.isEnabled(); 
        assertEquals(newIsEnabled, true);
    }

    @Test
    public void testSetIsEnabled() {
        userAll.setEnabled(false);
        assertEquals(false, userAll.isEnabled());
    }
}
