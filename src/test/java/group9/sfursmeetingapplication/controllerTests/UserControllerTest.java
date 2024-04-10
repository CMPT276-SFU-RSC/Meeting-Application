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

import group9.sfursmeetingapplication.models.User;

import static org.mockito.Mockito.when;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    
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

        User u2 = new User();
        u2.setEmail("email2");
        u2.setPassword("1234");

        List<User> users = new ArrayList<User>();
        users.add(u1);
        users.add(u2);  

        when(userRepo1.findall()).thenReturn(users);
        when(userRepo1.findByUid(30l)).thenReturn(u1);
        mockMvc.perform(MockMvcRequestBuilders.get("/admins/userdisplay").sessionAttr("user_id", (long)30))
            .andExpect(MockMvcResultMatchers.view().name("admins/display"))
            
            .andExpect(MockMvcResultMatchers.model().attribute("u1", hasItem(
                allOf(
                    hasProperty("email", Matchers.is("email1")),
                    hasProperty("password", Matchers.is("1234"))
                )
            )));
        mockMvc.perform(MockMvcRequestBuilders.get("/admins/userdisplay"))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }
    
    @Test
    void testDefault() throws Exception {
        //not logged in
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }

    @Test
    void testGetLogin() throws Exception {
        //not logged in
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
            .andExpect(MockMvcResultMatchers.view().name("users/login"));
    }

    @Test
    void registerEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
            .andExpect(MockMvcResultMatchers.view().name("users/registration"));
    }
    @Test
    void resendConfirmationEndPoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/resendConfirmation"))
            .andExpect(MockMvcResultMatchers.view().name("users/resendConfirmation"));
    }

    @Test
    void profileEndPoint() throws Exception {
        //not logged in
        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile"))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));

        
        User u1 = new User(); 
        u1.setFirstName("email1");
        u1.setPassword("1234");

        User u2 = new User();
        u2.setFirstName("email2");
        u2.setPassword("1234");

        List<User> users = new ArrayList<User>();
        users.add(u1);
        users.add(u2);  

        when(userRepo1.findall()).thenReturn(users);
        when(userRepo1.findByUid(30l)).thenReturn(u1);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile").sessionAttr("user_id", (long)30))
            .andExpect(MockMvcResultMatchers.view().name("users/profile"));
    }


    @Test
    void logout() throws Exception {
        //not logged in
        mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }




}