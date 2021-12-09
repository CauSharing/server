package causharing.causharing;

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




    //푸쉬푸쉬
    @Override
    public void run(ApplicationArguments args) throws Exception {


        try (Connection connection = dataSource.getConnection()) {

            /*User user1 = User.builder()
                    .email("test1@cau.ac.kr")
                    .department("College of Engineering")
                    .image("이미지1")
                    .language("KO")
                    .major("Architecture & Building Science")
                    .nickname("푸앙이1")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user1);

            User user2 = User.builder()
                    .email("test2@cau.ac.kr")
                    .department("College of Engineering")
                    .image("이미지2")
                    .language("KO")
                    .major("Mechanical Engineering")
                    .nickname("푸앙이2")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user2);

            User user3 = User.builder()
                    .email("test3@cau.ac.kr")
                    .department("College of Engineering")
                    .image("이미지3")
                    .language("EN")
                    .major("Advanced Materials Engineering")
                    .nickname("푸앙이3")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user3);

            User user4 = User.builder()
                    .email("test4@cau.ac.kr")
                    .department("College of Engineering")
                    .image("이미지4")
                    .language("EN")
                    .major("Architecture & Building Science")
                    .nickname("푸앙이4")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user4);

            User user5 = User.builder()
                    .email("test5@cau.ac.kr")
                    .department("College of Engineering")
                    .image("이미지5")
                    .language("KO")
                    .major("Mechanical Engineering")
                    .nickname("푸앙이5")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user5);

            User user6 = User.builder()
                    .email("test6@cau.ac.kr")
                    .department("College of Engineering")
                    .image("이미지6")
                    .language("KO")
                    .major("Advanced Materials Engineering")
                    .nickname("푸앙이6")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user6);

            User user7 = User.builder()
                    .email("test7@cau.ac.kr")
                    .department("College of Engineering")
                    .image("이미지7")
                    .language("EN")
                    .major("Architecture & Building Science")
                    .nickname("푸앙이7")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user7);

            User user8 = User.builder()
                    .email("test8@cau.ac.kr")
                    .department("College of Engineering")
                    .image("이미지8")
                    .language("EN")
                    .major("Mechanical Engineering")
                    .nickname("푸앙이8")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user8);

            User user9 = User.builder()
                    .email("test9@cau.ac.kr")
                    .department("College of Engineering")
                    .image("이미지9")
                    .language("KO")
                    .major("Advanced Materials Engineering")
                    .nickname("푸앙이9")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user9);

            User user10 = User.builder()
                    .email("test10@cau.ac.kr")
                    .department("College of Engineering")
                    .image("이미지10")
                    .language("EN")
                    .major("Energy Systems Engineering")
                    .nickname("푸앙이10")
                    .password(passwordEncoder.encode("string"))
                    .build();

            userRepository.save(user10);
            */
        }
            }



}
