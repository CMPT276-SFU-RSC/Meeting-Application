package group9.sfursmeetingapplication.serviceTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import group9.sfursmeetingapplication.models.*;
import group9.sfursmeetingapplication.controllers.*;
import group9.sfursmeetingapplication.services.*;
import group9.sfursmeetingapplication.repositories.*;

public class UserServiceImplementationTest {

    private UserServiceImplementation userService;
    private UserRepository userRepository;
    private ConfirmationRepository confirmationRepository;
    private EmailService emailService;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        confirmationRepository = mock(ConfirmationRepository.class);
        emailService = mock(EmailService.class);
        passwordEncoder = mock(BCryptPasswordEncoder.class);
        userService = new UserServiceImplementation(userRepository, confirmationRepository, emailService, passwordEncoder);
    }

    @Test
    public void testSaveUser_NewUser_Success() {
        // Arrange
        long generatedLong = 25;
        User user = new User(generatedLong, "organizer@yahoo.com", "password", "Harry", "Potter", "Robotics Team", "President", true, true);
   

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("hashedPassword");

        User savedUser = userService.saveUser(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
        assertFalse(savedUser.isEnabled()); // User should be disabled initially
        verify(userRepository, times(1)).save(user);
        verify(confirmationRepository, times(1)).save(any());
    }

    @Test
    public void testSaveUser_ExistingUser_ExceptionThrown() {
        // Arrange
        long generatedLong = 25; 
        User existingUser = new User(generatedLong, "organizer@yahoo.com", "password", "Harry", "Potter", "Robotics Team", "President", true, true);
   
        when(userRepository.existsByEmail(existingUser.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(existingUser));
        verify(userRepository, never()).save(any());
        verify(confirmationRepository, never()).save(any());
        verify(emailService, never()).sendSimpleMailMessage(any(), any(), any());
    }

    @Test
    public void testGetUser() {
        long generatedLong = 25;
        User user = new User(generatedLong, "organizer@yahoo.com", "password", "Harry", "Potter", "Robotics Team", "President", true, true);
        userRepository.save(user);
        // gotta fix this part
        User newUser = userService.getUser("organizer@yahoo.com"); 
        assertNull(newUser);

    }

    @Test
    public void testGetUserByID() {
        User userTest = new User(1L, "organizer@yahoo.com", "password", "Harry", "Potter", "Robotics Team", "President", true, true);
        userRepository.save(userTest);
        // gotta fix this part
        User newUser = userService.getUserById(1L);
        assertNull(newUser);


    }
}