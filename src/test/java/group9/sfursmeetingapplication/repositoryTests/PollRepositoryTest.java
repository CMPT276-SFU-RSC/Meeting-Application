package group9.sfursmeetingapplication.repositoryTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import group9.sfursmeetingapplication.repositories.PollRepository;
import group9.sfursmeetingapplication.models.Poll;

import java.time.Instant;
import java.util.List;

@DataJpaTest
public class PollRepositoryTest {

    @Autowired
    private PollRepository pollRepository;

    @Test
    public void testFindByPid() {

        List<Poll> polls = pollRepository.findBypid(1);
        assertEquals(0, polls.size());
        
        long generatedLong = 25;
        Instant startTime = Instant.parse("2024-03-25T12:00:00Z");
        Instant endTime = Instant.parse("2024-03-25T13:00:00Z");
        Instant expiryTime = Instant.parse("2024-03-27T12:00:00Z");
        Poll poll = new Poll(1, generatedLong, "Event", "random description", startTime, endTime, expiryTime);
   
        pollRepository.save(poll);
        polls = pollRepository.findBypid(1);
        assertEquals(1, polls.size());
        
        
    }

    @Test
    public void testFindByUID() {
        List<Poll> polls = pollRepository.findByUID(1L);
        assertEquals(0, polls.size());

        long generatedLong = 25;
        Instant startTime = Instant.parse("2024-03-25T12:00:00Z");
        Instant endTime = Instant.parse("2024-03-25T13:00:00Z");
        Instant expiryTime = Instant.parse("2024-03-27T12:00:00Z");
        Poll poll = new Poll(1, generatedLong, "Event", "random description", startTime, endTime, expiryTime);
   
        pollRepository.save(poll);
        polls = pollRepository.findByUID(1L);
        assertEquals(0, polls.size());
    }

    @Test
    public void testFind() {

        List<Poll> polls = pollRepository.find();
        assertNotNull(polls);
    }

    @Test
    public void testFindname() {

        List<Poll> polls = pollRepository.findname(1);
        assertNotNull(polls);
    }

}