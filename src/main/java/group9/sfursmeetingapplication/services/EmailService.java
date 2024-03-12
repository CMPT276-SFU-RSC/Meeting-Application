/**
 * The EmailService interface provides methods for sending different types of emails.
 */
package group9.sfursmeetingapplication.services;

public interface EmailService {

    void sendSimpleMailMessage(String name, String to, String token);
    // void sendMimeMessageWithAttachments(String name, String to, String subject,
    // String content, String token);
    // void sendMimeMessageWithEmbeddedImage(String name, String to, String subject,
    // String content, String token);
    // void sendMimeMessageWIthFiles(String name, String to, String subject, String
    // content, String token);
    // void sendHtmlEmail(String name, String to, String subject, String content,
    // String token);
    // void sendHtmlEmailWithEmbeddedFiles(String name, String to, String subject,
    // String content, String token);
}
