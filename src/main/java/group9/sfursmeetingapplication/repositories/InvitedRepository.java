package group9.sfursmeetingapplication.repositories;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import group9.sfursmeetingapplication.models.Invited;


public interface InvitedRepository extends JpaRepository<Invited, Integer> {

}
