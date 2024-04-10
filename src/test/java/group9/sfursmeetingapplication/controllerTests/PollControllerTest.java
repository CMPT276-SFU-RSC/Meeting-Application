package group9.sfursmeetingapplication.controllerTests;
import group9.sfursmeetingapplication.repositories.PollRepository;
import group9.sfursmeetingapplication.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import group9.sfursmeetingapplication.models.Poll;
import group9.sfursmeetingapplication.models.User;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PollControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserRepository userRepo1;
    
    @MockBean
    private PollRepository pollRepo;


    @Test
    void testUserpage() throws Exception {

        User u1 = new User(); 
        u1.setEmail("email1");
        u1.setPassword("1234");

        //login
        when(userRepo1.findByUid(30l)).thenReturn(u1);
        mockMvc.perform(MockMvcRequestBuilders.get("/polls/create").sessionAttr("user_id", (long)30))
            .andExpect(MockMvcResultMatchers.view().name("polls/pollcreate"));
        //not logged in
        mockMvc.perform(MockMvcRequestBuilders.get("/polls/create"))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }
    @Test
    void testCreatePoll() throws Exception {

        User u1 = new User(); 
        u1.setEmail("email1");
        u1.setPassword("1234");

        Poll p1 = new Poll(); 
        p1.setFinalized(true);
        //login
        when(userRepo1.findByUid(30l)).thenReturn(u1);
        when(pollRepo.findByPid(0)).thenReturn(p1);
        mockMvc.perform(MockMvcRequestBuilders.get("/polls/respond/0").sessionAttr("user_id", (long)30))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/dashboard"));
        //not logged in
        mockMvc.perform(MockMvcRequestBuilders.get("/polls/respond/0"))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }
    @Test
    void testUpdatePollResponse() throws Exception {

        User u1 = new User(); 
        u1.setEmail("email1");
        u1.setPassword("1234");

        Poll p1 = new Poll(); 
        p1.setFinalized(true);
        //login
        when(userRepo1.findByUid(30l)).thenReturn(u1);
        when(pollRepo.findByPid(0)).thenReturn(p1);
        mockMvc.perform(MockMvcRequestBuilders.get("/polls/updateResponse/0").sessionAttr("user_id", (long)30))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/dashboard"));
        //not logged in
        mockMvc.perform(MockMvcRequestBuilders.get("/polls/updateResponse/0"))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }
    @Test
    void testEditPoll() throws Exception {

        User u1 = new User(); 
        u1.setEmail("email1");
        u1.setPassword("1234");

        Poll p1 = new Poll(); 
        p1.setFinalized(true);

        when(userRepo1.findByUid(30l)).thenReturn(u1);
        when(pollRepo.findByPid(0)).thenReturn(p1);
        mockMvc.perform(MockMvcRequestBuilders.get("/polls/edit/0").sessionAttr("user_id", (long)30))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/dashboard"));

    }

    @Test
    void testVoteView() throws Exception {

        User u1 = new User(); 
        u1.setEmail("email1");
        u1.setPassword("1234");

        Poll p1 = new Poll(); 
        p1.setFinalized(true);

        //login
        when(userRepo1.findByUid(30l)).thenReturn(u1);
        when(pollRepo.findByPid(0)).thenReturn(p1);
        mockMvc.perform(MockMvcRequestBuilders.get("/polls/viewVotes/0").sessionAttr("user_id", (long)30))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/dashboard"));
        //not logged in
        mockMvc.perform(MockMvcRequestBuilders.get("/polls/viewVotes/0"))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }
}