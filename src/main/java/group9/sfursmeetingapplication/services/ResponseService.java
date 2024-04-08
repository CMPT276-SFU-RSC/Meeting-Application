/**
 * Interface for ResponseService
 */
package group9.sfursmeetingapplication.services;

import java.util.List;
import group9.sfursmeetingapplication.models.Response;

public interface ResponseService {

    /**
     * Save the user response
     * @param response The response object
     */
    void saveUserResponse(Response response);

    /**
     * Get all responses by uid
     * @param uid The user id
     * @return List<Response> The list of responses
     */
    List<Response> getAllResponsesByUid(Long uid);

}
