package group9.sfursmeetingapplication.serviceTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import group9.sfursmeetingapplication.models.*;
import group9.sfursmeetingapplication.controllers.*;
import group9.sfursmeetingapplication.services.*;
import group9.sfursmeetingapplication.repositories.*;

import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

class EmailServiceImplementationTest {

    @Mock
    private SendGrid sendGrid;

    @InjectMocks
    private EmailServiceImplementation emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendSimpleMailMessage_Success() throws IOException {
        // Arrange
        String name = "TestUser";
        String to = "test@example.com";
        String token = "testToken";
        when(sendGrid.api(any(Request.class))).thenReturn(new Response(202, "Accepted", null));

        // Act
        emailService.sendSimpleMailMessage(name, to, token);

        // Assert
        // Verify that sendGrid.api is called with the correct parameters
        verify(sendGrid, times(1)).api(any(Request.class));
    }

    @Test
    void testSendSimpleMailMessage_SendGridError() throws IOException {
        // Arrange
        String name = "TestUser";
        String to = "test@example.com";
        String token = "testToken";
        when(sendGrid.api(any(Request.class))).thenThrow(new IOException("SendGrid Error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> emailService.sendSimpleMailMessage(name, to, token));
    }
}