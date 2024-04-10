package group9.sfursmeetingapplication.controllerTests;
import group9.sfursmeetingapplication.repositories.PollRepository;
import group9.sfursmeetingapplication.repositories.ResponseRepository;
import group9.sfursmeetingapplication.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class PollControllerRestTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserRepository userRepo1;
    
    @MockBean
    private PollRepository pollRepo;

    @MockBean
    private ResponseRepository responseRepo;

    @Test
    void testGetMediumResponse() throws Exception {
        when(responseRepo.findByMidAndUid(0, 0)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/poll/response/0/0").sessionAttr("user_id", (long)30));
    }
    @Test
    void testGetUserResponse() throws Exception {
        when(responseRepo.findByMid(0)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/poll/response/0").sessionAttr("user_id", (long)30));
    }
}
