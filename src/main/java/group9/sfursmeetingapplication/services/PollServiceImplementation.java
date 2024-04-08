/**
 * This class is used to implement the PollService interface.
 * It includes a method to create a PollDTO from a Poll object.
 * 
 */
package group9.sfursmeetingapplication.services;

import org.springframework.stereotype.Service;
import group9.sfursmeetingapplication.dto.PollDTO;
import group9.sfursmeetingapplication.models.Poll;
import lombok.RequiredArgsConstructor;

@Service // This annotation is used to mark the class as a service provider.
@RequiredArgsConstructor // This annotation is used to generate a constructor with required fields.
public class PollServiceImplementation implements PollService {

    /**
     * This method creates a PollDTO from a Poll object.\
     * @param poll The poll object to be converted.
     * @param CreatorName The name of the creator of the poll.
     * @return The PollDTO object.
     */
    @Override
    public PollDTO createPollFromDTO(Poll poll, String CreatorName) {
        PollDTO pollDTO = new PollDTO();
        pollDTO.setPid(poll.getPid());
        pollDTO.setCreatorName(CreatorName);
        pollDTO.setTitle(poll.getTitle());
        pollDTO.setDescription(poll.getDescription());
        pollDTO.setStartDate(poll.getStartDate());
        pollDTO.setEndDate(poll.getEndDate());
        pollDTO.setExpirary(poll.getExpirary());
        pollDTO.setStartDateTimeString(pollDTO.formatDateTime(poll.getStartDate()));
        pollDTO.setEndDateTimeString(pollDTO.formatDateTime(poll.getEndDate()));
        pollDTO.setStartDateString(pollDTO.formatDate(poll.getStartDate()));
        pollDTO.setEndDateString(pollDTO.formatDate(poll.getEndDate()));
        pollDTO.setStartTimeString(pollDTO.formatTime(poll.getStartDate()));
        pollDTO.setEndTimeString(pollDTO.formatTime(poll.getEndDate()));
        return pollDTO;
    }
}
