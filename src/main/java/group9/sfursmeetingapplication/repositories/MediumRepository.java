package group9.sfursmeetingapplication.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import group9.sfursmeetingapplication.models.Medium;


public interface MediumRepository extends JpaRepository<Medium, Integer> {
     List<Medium> findBymid(Integer mid);
     List<Medium> findBypid(Integer pid);

     @Transactional
     void deleteBymid(Integer mid);
}
