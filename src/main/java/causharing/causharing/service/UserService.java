package causharing.causharing.service;


import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.RegisterApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User create(RegisterApiRequest request) {

        User user=User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .department(request.getDepartment())
                .major(request.getMajor())
                .nickname(request.getNickname())
                .reported_count(0)
                .reporter_count(3)
                .language(request.getLanguage())
                .build();

        return userRepository.save(user);
    }


    public boolean emailCheck(String email){
        User findUser = userRepository.findByEmail(email);

        if(findUser != null){ // 이미존재하는 email
            return false;
        }
        return true;
    }


    public boolean nicknameCheck(String nickname) {

        User findUser= userRepository.findByNickname(nickname);

        if(findUser!=null)
            return false;
        return true;
    }

    public boolean delete(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        else
            return false;
    }
}
