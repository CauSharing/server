package causharing.causharing.model.repository;

import causharing.causharing.model.entity.EditedData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditedDataRepository extends CrudRepository<EditedData, Long> {
}
