/**
 * Interface for PollService
 */
package group9.sfursmeetingapplication.services;

import group9.sfursmeetingapplication.dto.PollDTO;
import group9.sfursmeetingapplication.models.Poll;

public interface PollService {
    /**
     * This method creates a PollDTO from a Poll object.
     * 
     * @param poll        The poll object to be converted.
     * @param CreatorName The name of the creator of the poll.
     * @return The PollDTO object.
     */
    PollDTO createPollFromDTO(Poll poll, String CreatorName);

}
