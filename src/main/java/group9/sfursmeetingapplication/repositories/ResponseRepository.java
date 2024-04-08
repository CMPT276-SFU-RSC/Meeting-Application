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

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer>{
    List<Response> findByPid(int pid);

    @Transactional
    void deleteBymid(Integer mid);

    @Transactional
    void deleteByPid(Integer pid);

    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM user_responses " + 
                "WHERE user_responses.pid = ?1 AND user_responses.uid = ?2 ;",
        nativeQuery = true
    )
    void deleteByPidAndUid(int pid, int uid);
}