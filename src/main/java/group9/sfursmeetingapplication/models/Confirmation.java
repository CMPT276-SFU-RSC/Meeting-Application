/**
 * The Confirmation class represents a confirmation entity in the database.
 * It is used to confirm the user's email address.
 * Each Confirmation is associated with one User.
 */
package group9.sfursmeetingapplication.models;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // Lombok annotation to generate an empty constructor.
@Getter // Lombok annotation to generate the getters.
@Setter // Lombok annotation to generate the setters.
@Entity // JPA annotation to specify that the class is an entity.
@Table(name = "confirmations") // JPA annotation to specify the name of the database table to be used for the
                               // entity.
public class Confirmation {

    @Id // JPA annotation to specify the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA annotation to specify the primary key generation
                                                        // strategy.
    private Long cid; // The primary key of the confirmation entity.
    private String token; // The token used to confirm the user's email address.

    @CreatedDate // JPA annotation to specify the date and time when the confirmation was
                 // created.
    private LocalDateTime createdDate; // The date and time when the confirmation was created.

    /**
     * Creates the 1:1 relationship between the user and the confirmation and
     * fetches the user data eagerly when the
     * confirmation is fetched.
     */
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "uid")
    private User user; // The user associated with the confirmation.

    /**
     * Constructs a new Confirmation object for the given User.
     * 
     * @param user The User for whom this Confirmation is created.
     */
    public Confirmation(User user) {
        this.user = user;
        this.createdDate = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }
}
