package causharing.causharing.model.repository;

import causharing.causharing.model.entity.Chat;
import causharing.causharing.model.entity.Comment;
import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByMatchingRoomIdOrderByChatDateAsc(MatchingRoom matchingRoomId);

}