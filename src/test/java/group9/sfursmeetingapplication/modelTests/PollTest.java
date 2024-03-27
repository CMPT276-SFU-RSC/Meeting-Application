package group9.sfursmeetingapplication.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant; 

import group9.sfursmeetingapplication.models.Poll;

public class PollTest {

    private Poll poll;


    @BeforeEach
    void setUp() {
        long generatedLong = 25;
        Instant startTime = Instant.parse("2024-03-25T12:00:00Z");
        Instant endTime = Instant.parse("2024-03-25T13:00:00Z");
        Instant expiryTime = Instant.parse("2024-03-27T12:00:00Z");
        poll = new Poll(2, generatedLong, "Event", "random description", startTime, endTime, expiryTime);
    }

    @Test
    void testGetPid() {
        int newPid = poll.getPid();
        assertEquals(newPid, 2);
    }

    @Test
    void testSetPid() {
        poll.setPid(9);
        assertEquals(9, poll.getPid());
    }
    
    @Test 
    void testGetCreatorId() {
        long newCreatorId = poll.getCreator_id(); 
        assertEquals(newCreatorId, 25);
    }

    @Test 
    void testSetCreatorId() {
        long newCreatorId = 30;
        poll.setCreator_id(newCreatorId);
        assertEquals(30, poll.getCreator_id());
    }

    @Test 
    void testGetTitle() {
        assertEquals("Event", poll.getTitle());
    }

    @Test 
    void testSetTitle() {
        poll.setTitle("Other Event");
        assertEquals("Other Event", poll.getTitle());
    }

    @Test 
    void testGetDescription() {
        assertEquals("random description", poll.getDescription());
    }

    @Test 
    void testSetDescription() {
        poll.setDescription("Other Description");
        assertEquals("Other Description", poll.getDescription());
    }

    @Test 
    void testGetStartTime() {
        Instant startTime = Instant.parse("2024-03-25T12:00:00Z");
        assertEquals("2024-03-25T12:00:00Z", poll.getStartDate().toString());
    }

    @Test 
    void testSetStartTime() {
        Instant startTime = Instant.parse("2025-03-25T12:00:00Z");
        poll.setStartDate(startTime);
        assertEquals("2025-03-25T12:00:00Z", poll.getStartDate().toString());
    }

    @Test 
    void testGetEndTime() {
        Instant endTime = Instant.parse("2024-03-25T12:00:00Z");
        assertEquals("2024-03-25T13:00:00Z", poll.getEndDate().toString());
    }

    @Test 
    void testSetEndTime() {
        Instant endTime = Instant.parse("2025-03-25T12:00:00Z");
        poll.setEndDate(endTime);
        assertEquals("2025-03-25T12:00:00Z", poll.getEndDate().toString());
    }

    @Test 
    void testGetExpiryTime() {
        Instant expiryTime = Instant.parse("2024-03-27T12:00:00Z");
        assertEquals("2024-03-27T12:00:00Z", poll.getExpirary().toString());
    }

    @Test 
    void testSetExpiryTime() {
        Instant expiryTime = Instant.parse("2025-03-25T12:00:00Z");
        poll.setExpirary(expiryTime);
        assertEquals("2025-03-25T12:00:00Z", poll.getExpirary().toString());
    }
    
    
}
