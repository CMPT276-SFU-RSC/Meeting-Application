/**
 * Utility class for generating email messages and URLs for user verification.
 */
package group9.sfursmeetingapplication.utils;

public class EmailUtils {

    /**
     * Generates the email for user verification.
     * 
     * @param name  the name of the user
     * @param host  the host of the application
     * @param token the token for verifying the user's account
     * @return the email message
     */
    public static String getEmailMessage(String name, String host, String token) {
        return "Dear " + name + ",\n\n"
                + "Thank you for registering with us. Please click on the link below to verify your account:\n\n"
                + getVerificationUrl(host, token) + "\n\n"
                + "Best regards,\n"
                + "The SFURS Meeting Application Team";
    }

    public static String getEmailPasswordMessage(String name, String host, String token) {
        return "Dear " + name + ",\n\n"
                + "This is an email to reset your password. Please click on the link below:\n\n"
                + getResetPasswordUrl(host, token) + "\n\n"
                + "Best regards,\n"
                + "The SFURS Meeting Application Team";
    }

    /**
     * Gererates the verification URL for the user.
     * 
     * @param host  the host of the application
     * @param token the token for verifying the user's account
     * @return the verification URL
     */
    public static String getVerificationUrl(String host, String token) {
        return host + "/email/verified?token=" + token;
    }

    public static String getResetPasswordUrl(String host, String token) {
        return host + "/email/verifiedForgotPassword?token=" + token;
    }

}
