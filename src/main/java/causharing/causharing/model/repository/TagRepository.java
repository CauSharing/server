package causharing.causharing.model.repository;

import causharing.causharing.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select u from Tag u where u.matchingRoomId.matchingRoomId = ?1 and u.startDate like ?2% or u.endDate like ?2% order by u.startDate")
    List<Tag> findByTags(Long MatchingRoomId, String month);

}
