package causharing.causharing;

import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class Runner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            User user = User.builder()
                    .email("test@naver.com")
                    .department("공과")
                    .image("이미지")
                    .language("한국어")
                    .major("소프트웨어")
                    .nickname("퉁퉁이")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user);
        }
    }
}
