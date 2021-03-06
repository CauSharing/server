package causharing.causharing.service;


import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.RegisterApiRequest;
import causharing.causharing.model.response.UserProfileRequest;
import causharing.causharing.model.response.UserProfileResponse;
import io.swagger.annotations.ApiParam;
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

    /**
     * User Profile 확인 페이지
     */
    public UserProfileResponse read(String email) {
        User user = userRepository.findByEmail(email);

        return UserProfileResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .department(user.getDepartment())
                .major(user.getMajor())
                .image(user.getImage())
                .language(user.getLanguage())
                .build();
    }

    public UserProfileResponse update(String email,
                                       UserProfileRequest userProfileRequest) {
        User user = userRepository.findByEmail(email);

        user.setNickname(userProfileRequest.getNickname())
                .setDepartment(userProfileRequest.getDepartment())
                .setMajor(userProfileRequest.getMajor())
                .setImage(userProfileRequest.getImage())
                .setLanguage(userProfileRequest.getLanguage());

        userRepository.save(user);

        return UserProfileResponse.builder()
                .email(email)
                .nickname(user.getNickname())
                .department(user.getDepartment())
                .major(user.getMajor())
                .image(user.getImage())
                .language(user.getLanguage())
                .build();

    }
}
