/**
 * This is a Data Transfer Object class for the Invited entity.
 */
package group9.sfursmeetingapplication.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor // Lombok annotation to generate a constructor with all the arguments.
@NoArgsConstructor // Lombok annotation to generate an empty constructor.
@SuperBuilder // Lombok annotation to generate a builder for the class.
@Setter // Lombok annotation to generate the setters.
@Getter // Lombok annotation to generate the getters.
@ToString // Lombok annotation to generate the toString method.
public class InvitedDTO {
    private int iid;
    private int pid;
    private int uid;
    private String first_name;
    private String last_name;
}
