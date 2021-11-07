package causharing.causharing.model.repository;

import causharing.causharing.model.entity.Chat;
import causharing.causharing.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post  findByPostId(Long postId);
}
