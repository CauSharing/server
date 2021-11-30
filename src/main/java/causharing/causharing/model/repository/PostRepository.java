package causharing.causharing.model.repository;

import causharing.causharing.model.entity.Chat;
import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByPostId(Long postId);


    List<Post> findPostsByMatchingRoomIdAndPostDateBetween(MatchingRoom matchingRoom, LocalDateTime start, LocalDateTime end);

    List<Post> findPostsByPostDateBetween( LocalDateTime start, LocalDateTime end);

}
