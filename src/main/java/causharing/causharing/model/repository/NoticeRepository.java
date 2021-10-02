package causharing.causharing.model.repository;

import causharing.causharing.model.entity.Chat;
import causharing.causharing.model.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
