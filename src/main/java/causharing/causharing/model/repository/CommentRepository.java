package causharing.causharing.model.repository;

import causharing.causharing.model.entity.Chat;
import causharing.causharing.model.entity.Comment;
import causharing.causharing.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findByCommentId(Long CommentId);

    //게시글의 댓글 날짜 오름차순으로 뽑기
    List<Comment> findCommentsByPostIdOrderByCommentIdAsc(Post post);


}
