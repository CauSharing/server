package causharing.causharing.model.repository;

import causharing.causharing.model.entity.Matching;
import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Long> {


    List<Matching> findByUser(User user);

    List<Matching> findByMatchingRoomId(MatchingRoom matchingRoom);

    Matching findByMatchingRoomIdAndUser(MatchingRoom matchingRoom, User user);


}
