/**
 * Interface for ResponseRepository
 */
package group9.sfursmeetingapplication.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import group9.sfursmeetingapplication.models.Response;
import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer>{

    /**
     * Find all responses by UID
     * @param uid The user's UID
     * @return List<Response> The list of responses
     */
    List<Response> findByUid(Long uid);

    /**
     * This method gets response by the user's UID + MID + PID.
     * @param uid The user's UID.
     * @param mid medium ID.
     * @param pid poll ID.
     * @return Optional<Response> String of JSON
     */
    Optional<Response> findByUidAndMidAndPid(long uid, long mid, long pid);
}
