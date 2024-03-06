package group9.sfursmeetingapplication.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PollRepository extends JpaRepository<Poll, Integer> {
    List<Poll> findBypid(Integer pid);
}
