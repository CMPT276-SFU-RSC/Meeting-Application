/**
 * This interface provides the methods for the InvitedService class.
 */
package group9.sfursmeetingapplication.services;

import java.util.List;
import group9.sfursmeetingapplication.dto.InvitedDTO;

public interface InvitedService {
    /**
     * This method creates a list of InvitedDTO objects from the query results.
     * 
     * @param queryResults The query results.
     */
    List<InvitedDTO> createListOfInvitedFromDTO(List<Object[]> queryResults);

}
