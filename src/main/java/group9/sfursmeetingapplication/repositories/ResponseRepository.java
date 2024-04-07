/**
 * Interface for ResponseRepository
 */
package group9.sfursmeetingapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import group9.sfursmeetingapplication.models.Response;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer>{
}
