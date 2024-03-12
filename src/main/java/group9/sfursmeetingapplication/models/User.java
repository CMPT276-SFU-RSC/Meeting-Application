/**
 * The User class represents a user entity in the database.
 * It stores information about a user such as email, password, first name, last name, team, title, and roles.
 */
package group9.sfursmeetingapplication.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor // Lombok annotation to generate a constructor with all the arguments.
@NoArgsConstructor // Lombok annotation to generate an empty constructor.
@SuperBuilder // Lombok annotation to generate a builder for the class.
@Setter // Lombok annotation to generate the setters.
@Getter // Lombok annotation to generate the getters.
@ToString // Lombok annotation to generate the toString method.
@Entity // JPA annotation to specify that the class is an entity.
@Table(name = "users") // JPA annotation to specify the name of the database table to be used for the
                       // entity.
public class User {
    @Id // JPA annotation to specify the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA annotation to specify the primary key generation
                                                        // strategy.
    private Long uid; // The primary key of the user entity.
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String team; // The team to which the user belongs.
    private String title; // The title of the user or role of the user.
    private boolean isOrganizer; // Whether the user is an organizer.
    private boolean isEnabled; // Whether the user is enabled.

    
}




}

