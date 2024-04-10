package group9.sfursmeetingapplication.repositoryTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import group9.sfursmeetingapplication.models.Response;
import group9.sfursmeetingapplication.repositories.ResponseRepository;

@DataJpaTest
public class ResponseRepositoryTest {
    
    @Autowired
    private ResponseRepository responseRepository;

    @Test
    public void testSaveAndFind() {
        int mid = 1;
        int pid = 1;
        long uid = 2L;
        boolean remote = true;
        String medium = "Name";
        String available_time = "101";
        Response response = new Response();
        response.setPid(pid);
        response.setMid(mid);
        response.setUid(uid);
        response.setRemote(remote);
        response.setAvailable_time(available_time);
        response.setMedium(medium);
        responseRepository.save(response);
        Response newResponse = responseRepository.findByUidAndPidAndMid(uid, pid, mid);
        assertNotNull(newResponse);
    }

    @Test
    public void testFindByPid() {
        // Response 1
        int mid1 = 1;
        int pid1 = 1;
        long uid1 = 2L;
        boolean remote1 = true;
        String medium1 = "Name1";
        String available_time1 = "101";
        Response response1 = new Response();
        response1.setPid(pid1);
        response1.setMid(mid1);
        response1.setUid(uid1);
        response1.setRemote(remote1);
        response1.setAvailable_time(available_time1);
        response1.setMedium(medium1);
        responseRepository.save(response1);

        // Response 2
        int mid2 = 2;
        int pid2 = 1;
        long uid2 = 3L;
        boolean remote2 = false;
        String medium2 = "Name2";
        String available_time2 = "102";
        Response response2 = new Response();
        response2.setPid(pid2);
        response2.setMid(mid2);
        response2.setUid(uid2);
        response2.setRemote(remote2);
        response2.setAvailable_time(available_time2);
        response2.setMedium(medium2);
        responseRepository.save(response2);

        // Response 3
        int mid3 = 3;
        int pid3 = 1;
        long uid3 = 4L;
        boolean remote3 = true;
        String medium3 = "Name3";
        String available_time3 = "103";
        Response response3 = new Response();
        response3.setPid(pid3);
        response3.setMid(mid3);
        response3.setUid(uid3);
        response3.setRemote(remote3);
        response3.setAvailable_time(available_time3);
        response3.setMedium(medium3);
        responseRepository.save(response3);

        // Response 4
        int mid4 = 4;
        int pid4 = 1;
        long uid4 = 5L;
        boolean remote4 = false;
        String medium4 = "Name4";
        String available_time4 = "104";
        Response response4 = new Response();
        response4.setPid(pid4);
        response4.setMid(mid4);
        response4.setUid(uid4);
        response4.setRemote(remote4);
        response4.setAvailable_time(available_time4);
        response4.setMedium(medium4);
        responseRepository.save(response4);

        List<Response> foundResponse = responseRepository.findByPid(pid1);

        assertEquals(4, foundResponse.size());
    }

    @Test
    public void deleteByPid() {
        // Response 1
        int mid1 = 1;
        int pid1 = 1;
        long uid1 = 2L;
        boolean remote1 = true;
        String medium1 = "Name1";
        String available_time1 = "101";
        Response response1 = new Response();
        response1.setPid(pid1);
        response1.setMid(mid1);
        response1.setUid(uid1);
        response1.setRemote(remote1);
        response1.setAvailable_time(available_time1);
        response1.setMedium(medium1);
        responseRepository.save(response1);

        // Response 2
        int mid2 = 2;
        int pid2 = 1;
        long uid2 = 3L;
        boolean remote2 = false;
        String medium2 = "Name2";
        String available_time2 = "102";
        Response response2 = new Response();
        response2.setPid(pid2);
        response2.setMid(mid2);
        response2.setUid(uid2);
        response2.setRemote(remote2);
        response2.setAvailable_time(available_time2);
        response2.setMedium(medium2);
        responseRepository.save(response2);

        // Response 3
        int mid3 = 3;
        int pid3 = 1;
        long uid3 = 4L;
        boolean remote3 = true;
        String medium3 = "Name3";
        String available_time3 = "103";
        Response response3 = new Response();
        response3.setPid(pid3);
        response3.setMid(mid3);
        response3.setUid(uid3);
        response3.setRemote(remote3);
        response3.setAvailable_time(available_time3);
        response3.setMedium(medium3);
        responseRepository.save(response3);

        // Response 4
        int mid4 = 4;
        int pid4 = 1;
        long uid4 = 5L;
        boolean remote4 = false;
        String medium4 = "Name4";
        String available_time4 = "104";
        Response response4 = new Response();
        response4.setPid(pid4);
        response4.setMid(mid4);
        response4.setUid(uid4);
        response4.setRemote(remote4);
        response4.setAvailable_time(available_time4);
        response4.setMedium(medium4);
        responseRepository.save(response4);
        responseRepository.deleteByPid(pid1);
        List<Response> foundResponse = responseRepository.findByPid(pid1);

        assertEquals(0, foundResponse.size());
    }
    
}
