/**
 * UserController is a Spring Boot controller that handles HTTP requests related to users.
 * It uses UserService to perform operations related to users.
 */
package group9.sfursmeetingapplication.controllers;

import java.util.List;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor // Lombok annotation to generate the required constructor
@RestController
public class UserControllerRest {
    @Autowired
    private UserRepository userRepo;

    @PostMapping("/userSearch")
    public List<User> userSearch(@RequestBody String search) {
        List<User> res = userRepo.findBySearch(search);

        return res;
    }

    @GetMapping("/userAll")
    public List<User> userAll() {
        List<User> res = userRepo.findAll();
        return res;
    }

}
