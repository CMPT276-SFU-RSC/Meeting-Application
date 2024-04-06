/**
 * This class is a Data Transfer Object (DTO) class for the Poll entity.
 */
package group9.sfursmeetingapplication.dto;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.*;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor // Lombok annotation to generate a constructor with all the arguments.
@NoArgsConstructor // Lombok annotation to generate an empty constructor.
@SuperBuilder // Lombok annotation to generate a builder for the class.
@Setter // Lombok annotation to generate the setters.
@Getter // Lombok annotation to generate the getters.
@ToString // Lombok annotation to generate the toString method.
public class PollDTO {
    private int pid;
    private long creator_id;
    private String creatorName;
    private String title;
    private String description;
    private java.time.Instant startDate;
    private java.time.Instant endDate;
    private java.time.Instant expirary;
    private String startDateTimeString;
    private String endDateTimeString;
    private String startDateString;
    private String endDateString;
    private String startTimeString;
    private String endTimeString;
    
    /**
     * This method is used to format the date and time.
     * @param date The date to be formatted.
     * @return The formatted date.
     */
    public String formatDateTime(java.time.Instant date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd',' uuuu 'at' hh:mm a")
            .withZone(ZoneId.of("UTC"));
        return formatter.format(date);
    }

    /**
     * This method is used to format the date.
     * @param date The date to be formatted.
     * @return The formatted date.
     */
    public String formatDate(java.time.Instant date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd',' uuuu")
            .withZone(ZoneId.of("UTC"));
        return formatter.format(date);
    }

    /**
     * This method is used to format the time.
     * @param date The date to be formatted.
     * @return The formatted date.
     */
    public String formatTime(java.time.Instant date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm")
            .withZone(ZoneId.of("UTC"));
        return formatter.format(date);
    }
}
