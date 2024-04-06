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
    private String start;
    private String end;
    private String expire;
    private java.time.Instant startDate;
    private java.time.Instant endDate;
    private java.time.Instant expirary;
    
    /**
     * This method is used to format the time.
     * @param date The date to be formatted.
     * @return The formatted date.
     */
    public String formatTime(java.time.Instant date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd',' uuuu 'at' hh:mm a")
            .withZone(ZoneId.of("UTC"));
        return formatter.format(date);
    }
}
