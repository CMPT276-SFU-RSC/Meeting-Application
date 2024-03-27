package group9.sfursmeetingapplication.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import group9.sfursmeetingapplication.models.Invited;

public class InvitedTest {
    private Invited invited; 

    @BeforeEach
    void setUp() {
        invited = new Invited(1, 2, 3);
    }


    @Test
    void testGetPid() {
        assertEquals(1, invited.getPid());
    }

    @Test
    void testSetPid() {
        invited.setPid(10);
        assertEquals(10, invited.getPid());
    }

    @Test
    void testGetUid() {
        assertEquals(2, invited.getUid());
    }

    @Test
    void testSetUid() {
        invited.setUid(20);
        assertEquals(20, invited.getUid());
    }

    @Test
    void testGetIid() {
        assertEquals(3, invited.getIid());
    }

    @Test
    void testSetIid() {
        invited.setIid(30);
        assertEquals(30, invited.getIid());
    }

    
}
