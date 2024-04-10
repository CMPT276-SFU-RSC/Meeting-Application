package group9.sfursmeetingapplication.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import group9.sfursmeetingapplication.models.Poll;
import jakarta.transaction.Transactional;

public interface PollRepository extends JpaRepository<Poll, Integer> {

        /**
         * Find a poll by its PID
         */
        Poll findByPid(Integer pid);

        List<Poll> findBypid(Integer pid);

        @Query(value = "SELECT polls.* " +
                        "FROM polls, invited " +
                        "WHERE invited.uid = ?1 AND polls.pid = invited.pid " +
                        "ORDER BY polls.start_date;",
                        nativeQuery = true)
        List<Poll> findByUID(Long uid);

        @Query(value = "SELECT DISTINCT uid" +
                        "FROM user_responses, polls" +
                        "WHERE pid = ?1 AND polls.pid = user_responses.pid", nativeQuery = true)
        List<Integer> findResponseByPID(Integer pid);

        @Query(value = "SELECT DISTINCT uid" +
                        "FROM invited, polls" +
                        "WHERE pid = ?1 AND polls.pid = invited.pid", nativeQuery = true)
        List<Integer> findInvitedByPID(Integer pid);

        @Query(value = "SELECT polls.* " +
                        "FROM polls, invited " +
                        "WHERE polls.finalized = FALSE AND invited.uid = ?1 AND polls.pid = invited.pid " +
                        "ORDER BY polls.start_date;",
                        nativeQuery = true)
        List<Poll> findByUIDopen(Long uid);

        @Query(value = "SELECT * FROM polls", nativeQuery = true)
        List<Poll> find();

        @Query(value = "SELECT DISTINCT pid" +
                        "FROM polls", nativeQuery = true)
        List<Poll> findDistinctPID();

        @Query(value = "SELECT * FROM polls WHERE pid = ?1", nativeQuery = true)
        List<Poll> findname(Integer pid);

        /**
         * Find all polls created by a user
         * 
         * @param userUid The user's UID
         * @return A list of polls created by the user
         */
        @Query(value = "SELECT * FROM polls WHERE creator_id = :userUid", nativeQuery = true)
        List<Poll> findByCreator_id(@Param("userUid") Long userUid);

        @Query(value = "SELECT * FROM polls WHERE polls.finalized = FALSE AND creator_id = :userUid", nativeQuery = true)
        List<Poll> findByCreator_idopen(@Param("userUid") Long userUid);

        @Query(value = "SELECT DISTINCT polls.* " +
                        "FROM polls, invited " +
                        "WHERE polls.finalized IS TRUE AND ( (polls.pid = invited.pid AND invited.uid = ?1) OR (polls.creator_id = ?1) ) ;", nativeQuery = true)
        List<Poll> findFinalizedViewable(long uid);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM polls " +
                        "WHERE polls.creator_id = ?1 ;", nativeQuery = true)
        void deleteByCreatoruid(long uid);

}
