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

@SpringBootTest
@AutoConfigureMockMvc
public class EmailControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserRepository userRepo1;
    
    @MockBean
    private PollRepository pollRepo;


    @Test
    void testResendConfirmation() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/email/resendConfirmation"))
            .andExpect(MockMvcResultMatchers.view().name("emails/resendConfirmation"));

    }
    @Test
    void testResetPasswordPage() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/email/forgotPassword"))
            .andExpect(MockMvcResultMatchers.view().name("emails/forgotPassword"));

    }
}
