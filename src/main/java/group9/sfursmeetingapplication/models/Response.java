/**
 * This is a model class for the Response entity.
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
@Table(name = "user_responses") // JPA annotation to specify the details of the table that this entity will be mapped to.
public class Response {
    @Id // JPA annotation to specify the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA annotation to specify the primary key generation
    private Integer rid; // The primary key of the response entity (Used for Indexing).
    private Long uid;
    private Integer mid;
    private Integer pid;
    private Boolean remote;
    private String medium;
    private String available_time;
}