/**
 * The EmailServiceImplementation class provides an implementation of the EmailService interface.
 * It includes methods to send different types of emails.
 */
package group9.sfursmeetingapplication.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import group9.sfursmeetingapplication.utils.EmailUtils;
import lombok.RequiredArgsConstructor;

@Service // Spring Framework annotation for indicating that this class is a service
@RequiredArgsConstructor // Lombok annotation for generating a constructor with required arguments
public class EmailServiceImplementation implements EmailService {
    private static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    private static final String NEW_EVENT = "New Event";

    @Value("${spring.mail.properties.verify.host}") // Spring Framework annotation for injecting a value from a property
                                                    // file.
    private String host;

    @Value("${spring.mail.username}") // Spring Framework annotation for injecting a value from a property file
    private String fromEmail;

    @Value("${spring.sendgrid.api-key}") // Spring Framework annotation for injecting a value from a property file
    private String apiKey;

    /**
     * The SendGrid object for sending emails.
     */
    private SendGrid sendGrid;

    /**
     * Sends a simple mail message to the specified email address.
     * 
     * @param name  the name of the user
     * @param to    the email address of the user
     * @param token the token for verifying the user's account
     * @throws RuntimeException if an error occurs while sending the email
     */
    @Override
    @Async // Spring Framework annotation for indicating that this method should be
           // executed asynchronously
    public void sendSimpleMailMessage(String name, String to, String token) {
        Email sendGridFrom = new Email(fromEmail);
        String sendGridSubject = NEW_USER_ACCOUNT_VERIFICATION;
        Email sendGridTo = new Email(to);
        Content sendGridContent = new Content("text/plain", EmailUtils.getEmailMessage(name, host, token));
        Mail sendGridMail = new Mail(sendGridFrom, sendGridSubject, sendGridTo, sendGridContent);
        sendGrid = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(sendGridMail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw new RuntimeException("Error: " + ex.getMessage());
        }
    }
    public void sendEventMessage(String name, String to) {
        Email sendGridFrom = new Email(fromEmail);
        String sendGridSubject = NEW_EVENT;
        Email sendGridTo = new Email(to);
        Content sendGridContent = new Content("text/plain", EmailUtils.getEventMessage(name));
        Mail sendGridMail = new Mail(sendGridFrom, sendGridSubject, sendGridTo, sendGridContent);
        sendGrid = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(sendGridMail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw new RuntimeException("Error: " + ex.getMessage());
        }
    }
}
