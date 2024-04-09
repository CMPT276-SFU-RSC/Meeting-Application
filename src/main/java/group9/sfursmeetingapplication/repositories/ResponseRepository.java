/**
 * Interface for ResponseRepository
 */
package group9.sfursmeetingapplication.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import group9.sfursmeetingapplication.models.Response;
import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {
    List<Response> findByPid(int pid);

    /**
     * This method gets the response by the user's UID + PID + MID.
     * @param uid The user's UID.
     * @param pid The poll ID.
     * @param mid The medium ID.
     * @return Response The response object.
     */
    Response findByUidAndPidAndMid(Long uid, Integer pid, Integer mid);

    @Transactional
    void deleteBymid(Integer mid);

    @Transactional
    void deleteByPid(Integer pid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_responses " +
            "WHERE user_responses.pid = ?1 AND user_responses.uid = ?2 ;", nativeQuery = true)
    void deleteByPidAndUid(int pid, int uid);

    /**
     * Find all responses by UID
     * 
     * @param uid The user's UID
     * @return List<Response> The list of responses
     */
    List<Response> findByUid(Long uid);

    /**
     * This method gets response by the user's UID + MID + PID.
     * 
     * @param uid The user's UID.
     * @param mid medium ID.
     * @param pid poll ID.
     * @return Optional<Response> String of JSON
     */
    Optional<Response> findByUidAndMidAndPid(long uid, long mid, long pid);

    @Query(value = "SELECT user_responses.* " +
            "FROM user_responses " +
            "WHERE user_responses.mid = ?1 AND user_responses.uid = ?2 ;", nativeQuery = true)
    List<Response> findByMidAndUid(int mid, int uid);

    @Query(value = "SELECT user_responses.* " +
            "FROM user_responses " +
            "WHERE user_responses.mid = ?1 ;", nativeQuery = true)
    List<Response> findByMid(int mid);

}