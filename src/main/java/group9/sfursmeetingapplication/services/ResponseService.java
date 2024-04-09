/**
 * Interface for ResponseService
 */
package group9.sfursmeetingapplication.services;

import java.util.List;
import group9.sfursmeetingapplication.models.Response;
import java.util.Optional;

public interface ResponseService {

    /**
     * Save the user response
     * 
     * @param response The response object
     */
    void saveUserResponse(Response response);

    /**
     * Get all responses by uid
     * 
     * @param uid The user id
     * @return List<Response> The list of responses
     */
    List<Response> getAllResponsesByUid(Long uid);

    /**
     * Get response by uid+mid+pid
     * 
     * @param uid The user id
     * @param mid The medium id
     * @param pid The poll id
     * @return Optional<Response> string of JSON
     */
    Optional<Response> getUserResponseByUidMidPid(long uid, long mid, long pid);

    /**
     * Update the user response
     * 
     * @param response The response object
     */
    void updateUserResponse(Response response);
}
