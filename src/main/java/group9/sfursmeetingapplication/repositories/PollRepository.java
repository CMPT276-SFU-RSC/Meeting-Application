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

    List<Poll> findByUID(Long uid);
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

   


   /* 
    @Query(
        value = "SELECT * FROM users",
        nativeQuery = true
    )
    List<User> finder1();*/

   
}
