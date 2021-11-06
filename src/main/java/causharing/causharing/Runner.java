package causharing.causharing;

import causharing.causharing.model.entity.Matching;
import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.MatchingRepository;
import causharing.causharing.model.repository.MatchingRoomRepository;
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

    @Autowired
    MatchingRepository matchingRepository;

    @Autowired
    MatchingRoomRepository matchingRoomRepository;



    @Override
    public void run(ApplicationArguments args) throws Exception {


        try (Connection connection = dataSource.getConnection()) {

            /*
            User user = User.builder()
                    .email("jng@naver.com")
                    .department("공과")
                    .image("이미지")
                    .language("한국어")
                    .major("소프트웨어")
                    .nickname("조나경")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user);

            User user2 = User.builder()
                    .email("jng2@naver.com")
                    .department("공과")
                    .image("이미지")
                    .language("한국어")
                    .major("소프트웨어")
                    .nickname("정지광")
                    .password(passwordEncoder.encode("string"))
                    .build();


            userRepository.save(user2);


            User user3 = User.builder()
                    .email("jng3@naver.com")
                    .department("공과")
                    .image("이미지")
                    .language("한국어")
                    .major("소프트웨어")
                    .nickname("강민주")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user3);

*/
        }


            }



}
