package group9.sfursmeetingapplication.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import group9.sfursmeetingapplication.models.Invited;


public interface InvitedRepository extends JpaRepository<Invited, Integer> {

}
