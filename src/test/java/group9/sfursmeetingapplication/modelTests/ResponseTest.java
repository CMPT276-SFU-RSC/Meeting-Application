package group9.sfursmeetingapplication.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import group9.sfursmeetingapplication.models.Response;

@SpringBootTest
public class ResponseTest {

    private Response response;

    @BeforeEach
    void setUp() {
        response = new Response(25, 30L, 20, 10, true, "Zoom", "2024-03-25T12:00:00Z");
    }

    @Test
    void testGetRid() {
        int newRid = response.getRid();
        assertEquals(newRid, 25);
    }

    @Test
    void testSetRid() {
        response.setRid(9);
        assertEquals(9, response.getRid());
    }

    @Test
    void testGetUid() {
        long newUid = response.getUid();
        assertEquals(newUid, 30L);
    }

    @Test
    void testSetUid() {
        long newUid = 35L;
        response.setUid(newUid);
        assertEquals(35L, response.getUid());
    }

    @Test
    void testGetMid() {
        int newMid = response.getMid();
        assertEquals(newMid, 20);
    }

    @Test
    void testSetMid() {
        response.setMid(15);
        assertEquals(15, response.getMid());
    }

    @Test
    void testGetPid() {
        int newPid = response.getPid();
        assertEquals(newPid, 10);
    }

    @Test
    void testSetPid() {
        response.setPid(5);
        assertEquals(5, response.getPid());
    }

    @Test
    void testGetRemote() {
        boolean newRemote = response.getRemote();
        assertEquals(newRemote, true);
    }

    @Test
    void testSetRemote() {
        response.setRemote(false);
        assertEquals(false, response.getRemote());
    }

    @Test
    void testGetMedium() {
        assertEquals("Zoom", response.getMedium());
    }

    @Test
    void testSetMedium() {
        response.setMedium("Google Meet");
        assertEquals("Google Meet", response.getMedium());
    }

    @Test
    void testGetAvailableTime() {
        assertEquals("2024-03-25T12:00:00Z", response.getAvailable_time().toString());
    }

    @Test
    void testSetAvailableTime() {
        response.setAvailable_time("2025-03-25T12:00:00Z");
        assertEquals("2025-03-25T12:00:00Z", response.getAvailable_time().toString());
    }

    @Test
    void testToString() {
        assertEquals("Response(rid=25, uid=30, mid=20, pid=10, remote=true, medium=Zoom, available_time=2024-03-25T12:00:00Z)",
                response.toString());
    }

    @Test
    void testNoArgsConstructor() {
        Response newResponse = new Response();
        assertEquals(null, newResponse.getRid());
    }

    @Test
    void testAllArgsConstructor() {
        Response newResponse = new Response(25, 30L, 20, 10, true, "Zoom", "2024-03-25T12:00:00Z");
        assertEquals(25, newResponse.getRid());
    }

    @Test
    void testSuperBuilder() {
        Response newResponse = Response.builder().rid(25).uid(30L).mid(20).pid(10).remote(true).medium("Zoom")
                .available_time("2024-03-25T12:00:00Z").build();
        assertEquals(25, newResponse.getRid());
    }
    
}
