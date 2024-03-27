package group9.sfursmeetingapplication.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import group9.sfursmeetingapplication.models.Medium;

@SpringBootTest
public class MediumTest {

    private Medium medium; 

    @BeforeEach
    void setUp() {
        medium = new Medium(1,"Burnaby", false);
    }

    @Test
    void testGetMid() {
        assertEquals(1, medium.getMid());
    }

    @Test
    void testSetMid() {
        medium.setMid(2);
        assertEquals(2, medium.getMid());
    }

    @Test
    void testGetName() {
        assertEquals("Burnaby", medium.getName());
    }

    @Test
    void testSetName() {
        medium.setName("Surrey");
        assertEquals("Surrey", medium.getName());
    }

    @Test
    void testGetRemote() {
        assertEquals(false, medium.getRemote());
    }

    @Test
    void testSetRemote() {
        medium.setRemote(true);
        assertEquals(true, medium.getRemote());
    }

    
    
}
