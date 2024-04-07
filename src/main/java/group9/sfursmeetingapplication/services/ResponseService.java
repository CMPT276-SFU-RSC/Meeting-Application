/**
 * Interface for ResponseService
 */
package group9.sfursmeetingapplication.services;

import group9.sfursmeetingapplication.models.Response;

public interface ResponseService {

    /**
     * Save the user response
     * @param response The response object
     */
    void saveUserResponse(Response response);
}
