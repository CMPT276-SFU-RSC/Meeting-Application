/**
 * The EmailServiceImplementation class provides an implementation of the EmailService interface.
 * It includes methods to send different types of emails.
 */
package group9.sfursmeetingapplication.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import group9.sfursmeetingapplication.utils.EmailUtils;
import lombok.RequiredArgsConstructor;

@Service // Spring Framework annotation for indicating that this class is a service
@RequiredArgsConstructor // Lombok annotation for generating a constructor with required arguments
public class EmailServiceImplementation implements EmailService {
    private static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";

    @Value("${spring.mail.properties.verify.host}") // Spring Framework annotation for injecting a value from a property
                                                    // file
    private String host;

    @Value("${spring.mail.username}") // Spring Framework annotation for injecting a value from a property file
    private String fromEmail;
    private final JavaMailSender emailSender;

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
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(EmailUtils.getEmailMessage(name, host, token));
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    // @Override
    // @Async
    // public void sendMimeMessageWithAttachments(String name, String to, String
    // subject, String content, String token) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'sendMimeMessageWithAttachments'");
    // }

    // @Override
    // @Async
    // public void sendMimeMessageWithEmbeddedImage(String name, String to, String
    // subject, String content, String token) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'sendMimeMessageWithEmbeddedImage'");
    // }

    // @Override
    // @Async
    // public void sendMimeMessageWIthFiles(String name, String to, String subject,
    // String content, String token) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'sendMimeMessageWIthFiles'");
    // }

    // @Override
    // @Async
    // public void sendHtmlEmail(String name, String to, String subject, String
    // content, String token) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'sendHtmlEmail'");
    // }

    // @Override
    // @Async
    // public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String
    // subject, String content, String token) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'sendHtmlEmailWithEmbeddedFiles'");
    // }

}
