/**
 * This class is used to implement the ResponseService interface. It is used to save the user's response to the database.
 */
package group9.sfursmeetingapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import group9.sfursmeetingapplication.models.Response;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.ResponseRepository;
import lombok.RequiredArgsConstructor;

@Service // This annotation is used to mark the class as a service provider
@RequiredArgsConstructor // This annotation is used to generate a constructor with required fields
public class ResponseServiceImplementation implements ResponseService {

    @Autowired
    private final ResponseRepository responseRepository;
    private final UserService userService;

    /**
     * This method saves the user's response to the database.
     * @param response The response object.
     * @throws IllegalArgumentException If the user does not exist.
     * 
     */
    @Override
    public void saveUserResponse(Response response) {
        User user = userService.getUserById(response.getUid());
        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        responseRepository.save(response);
    }
    
}
