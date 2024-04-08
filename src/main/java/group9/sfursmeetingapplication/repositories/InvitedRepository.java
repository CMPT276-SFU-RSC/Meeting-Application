package group9.sfursmeetingapplication.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import group9.sfursmeetingapplication.models.Invited;
import group9.sfursmeetingapplication.models.User;

import java.util.List;



public interface InvitedRepository extends JpaRepository<Invited, Integer> {
    List<User> findByPid(int pid);


    @Transactional
    void deleteBypid(Integer pid);

    List<Invited> findByPidAndUid(int pid, int uid);
}
