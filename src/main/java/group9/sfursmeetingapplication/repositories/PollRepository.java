package group9.sfursmeetingapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    List<Poll> findByUID(long uid);
}
