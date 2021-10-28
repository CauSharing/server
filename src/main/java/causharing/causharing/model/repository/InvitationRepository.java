package causharing.causharing.model.repository;

import causharing.causharing.model.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    // 초대 목록을 보기 위한 메소드
    @Query("select u from Invitation u where u.InvitedPerson = ?1")
    List<Invitation> findByInvitedPerson(String email);
}
