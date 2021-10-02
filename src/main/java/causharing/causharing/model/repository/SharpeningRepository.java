package causharing.causharing.model.repository;

import causharing.causharing.model.entity.Chat;
import causharing.causharing.model.entity.Sharpening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharpeningRepository extends JpaRepository<Sharpening, Long> {
}
