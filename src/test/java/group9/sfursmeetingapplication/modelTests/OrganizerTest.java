package group9.sfursmeetingapplication.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import group9.sfursmeetingapplication.models.Organizer;

public class OrganizerTest {

    private Organizer organizer; 

    @BeforeEach
    void setUp() {
        long generatedLong = 25;
        organizer = new Organizer(generatedLong, "organizer@yahoo.com", "password", "Harry", "Potter", "Robotics Team", "President", true, true, "additional field");
    }

    @Test
    void testGetAdditionalField() {
        assertEquals("additional field", organizer.getAdditionalField());
    }

    @Test
    void testSetAdditionalField() {
        organizer.setAdditionalField("other field");
        assertEquals("other field", organizer.getAdditionalField());
    }

    // since organier class extends user class, testing the other methods is redundant

    
}
