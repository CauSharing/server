package causharing.causharing.security.service;

import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.UserRepository;
import lombok.SneakyThrows;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * DB에서 UserDetail를 얻어와 AuthenticationManager에게
 * 제공하는 역할
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new Exception("해당 유저를 찾지 못했습니다.");
        }

        // email로 user객체를 찾고 유저의 이메일과 비밀번호를 가진 UserDetail객체 리턴
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
