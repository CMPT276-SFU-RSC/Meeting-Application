/**
 * This class is used to implement the methods of the InvitedService interface.
 */
package group9.sfursmeetingapplication.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import group9.sfursmeetingapplication.dto.InvitedDTO;

@Service // This annotation is used to mark the class as a service provider.
@RequiredArgsConstructor // This annotation is used to generate a constructor with required fields.
public class InvitedServiceImplementation implements InvitedService {

    /**
     * This method creates a list of InvitedDTO objects from the query results.
     * 
     * @param queryResults The query results.
     * @return The list of InvitedDTO objects.
     */
    @Override
    public List<InvitedDTO> createListOfInvitedFromDTO(List<Object[]> queryResults) {
        List<Object[]> results = queryResults;
        List<InvitedDTO> invitedDTOS = new ArrayList<>();

        for (Object[] result : results) {
            InvitedDTO dto = new InvitedDTO();
            // Result values are based on the order of the qurety in the InvitedRepository
            dto.setIid((Integer) result[0]);
            dto.setPid((Integer) result[1]);
            dto.setUid((Integer) result[2]);
            dto.setFirst_name((String) result[3]);
            dto.setLast_name((String) result[4]);
            invitedDTOS.add(dto);
        }
        return invitedDTOS;
    }

}
