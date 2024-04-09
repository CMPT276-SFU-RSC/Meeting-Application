package group9.sfursmeetingapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import group9.sfursmeetingapplication.models.Poll;



public interface PollRepository extends JpaRepository<Poll, Integer> {
    List<Poll> findBypid(Integer pid);

    @Query(
        value = "SELECT polls.* " + 
                "FROM polls, invited " +
                "WHERE invited.uid = ?1 AND polls.pid = invited.pid " + 
                "ORDER BY polls.start_date;", //order by the first possible start time, could be changed later (maybe priority system)
        nativeQuery = true
    )
    List<Poll> findByUID(Long uid);

    @Query(
        value = "SELECT DISTINCT uid" + 
                "FROM user_responses, polls" + 
                "WHERE pid = ?1 AND polls.pid = user_responses.pid",
        nativeQuery = true
    )
 
    List<Integer> findResponseByPID(Integer pid);

    @Query(
        value = "SELECT DISTINCT uid" + 
                "FROM invited, polls" + 
                "WHERE pid = ?1 AND polls.pid = invited.pid",
        nativeQuery = true
    )
 
    List<Integer> findInvitedByPID(Integer pid);

    @Query(
        value = "SELECT * FROM polls",
        nativeQuery = true
    )
    List<Poll> find();

    @Query(
        value = "SELECT * FROM polls WHERE pid = ?1",
        nativeQuery = true
    )
    List<Poll> findname(Integer pid);

    /**
     * Find all polls created by a user
     * @param userUid The user's UID
     * @return A list of polls created by the user
     */
    @Query(value = "SELECT * FROM polls WHERE creator_id = :userUid", nativeQuery = true)
    List<Poll> findByCreator_id(@Param("userUid") Long userUid);

    /**
     * Find a poll by its PID
     */
    Poll findByPid(Integer pid);

   
}
