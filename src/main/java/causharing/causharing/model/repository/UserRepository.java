package causharing.causharing.model.repository;

import causharing.causharing.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    @Query(value = "SELECT u FROM User u WHERE u.nickname = ?1")
    User findByNickname(String nickname);

    // 유저 초대를 위한 메소드
    User findTop1ByDepartmentAndMajorAndLanguageAndEmailNotOrderByMatchingCountAsc(String department, String major, String language, String userEmail);

    User findTop1ByDepartmentAndMajorAndLanguageAndEmailNotAndEmailNotOrderByMatchingCountAsc(String department, String major, String language, String userEmail1, String userEmail2);
}
