package causharing.causharing.model.repository;

import causharing.causharing.model.entity.MatchingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingRoomRepository extends JpaRepository<MatchingRoom, Long> {

    MatchingRoom findByMatchingRoomId(Long roomId);
}
