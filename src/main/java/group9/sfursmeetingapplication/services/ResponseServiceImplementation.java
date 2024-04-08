/**
 * This class is used to implement the ResponseService interface. It is used to save the user's response to the database.
 */
package group9.sfursmeetingapplication.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import group9.sfursmeetingapplication.models.Response;
import group9.sfursmeetingapplication.models.User;
import group9.sfursmeetingapplication.repositories.ResponseRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

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


    /**
     * This method gets all the responses by the user's UID.
     * @param uid The user's UID.
     * @return List<Response> The list of responses.
     */
    @Override
    public List<Response> getAllResponsesByUid(Long uid) {
        User user = userService.getUserById(uid);
        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        return responseRepository.findByUid(uid);
    }  
    
    /**
     * This method gets response by the user's UID + MID + PID.
     * @param uid The user's UID.
     * @param mid medium ID.
     * @param pid poll ID.
     * @return Optional<Response> String of JSON
     */
    @Override
    public Optional<Response> getUserResponseByUidMidPid(long uid, long mid, long pid) {
        return responseRepository.findByUidAndMidAndPid(uid, mid, pid);
    }

}
