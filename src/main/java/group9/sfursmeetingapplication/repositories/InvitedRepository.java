/**
 * This interface is a repository for the Invited model.
 */
package group9.sfursmeetingapplication.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import group9.sfursmeetingapplication.models.Invited;

public interface InvitedRepository extends JpaRepository<Invited, Integer> {
    /**
     * This method finds all the invited users for a specific meeting.
     * 
     * @param pid The meeting's PID
     * @return A list of the invited users.
     */
    @Query(value = "SELECT invited.iid, invited.pid, invited.uid, users.first_name, users.last_name " +
            "FROM invited " +
            "INNER JOIN users ON invited.uid = users.uid " +
            "WHERE invited.pid = :pid ", nativeQuery = true)
    List<Object[]> findByPid(@Param("pid") Integer pid);

}
