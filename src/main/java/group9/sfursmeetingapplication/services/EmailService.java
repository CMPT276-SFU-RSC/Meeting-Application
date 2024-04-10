/**
 * The EmailService interface provides methods for sending different types of emails.
 */
package group9.sfursmeetingapplication.services;

public interface EmailService {

    void sendSimpleMailMessage(String name, String to, String token);

    void sendEventMessage(String name, String to);

    public void sendSimplePasswordMailMessage(String name, String to, String token);

    public void sendPollReadyMessage(String name, String to);
    
}
