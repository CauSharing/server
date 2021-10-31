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
/*

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
            //매칭룸 만들엉용
            MatchingRoom room= MatchingRoom.builder().matchingRoomId(1L).build();
            matchingRoomRepository.save(room);

            MatchingRoom room2= MatchingRoom.builder().matchingRoomId(2L).build();
            matchingRoomRepository.save(room2);

            //매칭 만들어욥
            Matching m= Matching.builder().MatchingId(1L).matchingRoomId(room)
                            .user(user).build();
            Matching m2= Matching.builder().MatchingId(2L).matchingRoomId(room2)
                    .user(user).build();


            matchingRepository.save(m);
            matchingRepository.save(m2);

        }
*/

            }



}
