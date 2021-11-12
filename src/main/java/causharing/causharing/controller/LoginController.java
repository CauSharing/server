package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.LoginRequest;
import causharing.causharing.model.response.LoginResponse;
import causharing.causharing.security.entity.JwtUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "로그인 페이지", notes = "JWT 토큰을 전달")
    @PostMapping("/login")
    @CrossOrigin(origins="*", maxAge=3600)
    public Header generateToken(
            @ApiParam(value = "!!이메일 주소, 비밀번호 필수!!", required = true, example = "email:test@naver.com, password:****")
            @RequestBody LoginRequest loginRequest
    ) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            // TODO : 유저 정보 같이 보내기.
            User user = userRepository.findByEmail(loginRequest.getEmail());
            LoginResponse loginResponse = LoginResponse.builder()
                    .jwtToken(jwtUtil.generateToken(loginRequest.getEmail()))
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .image(user.getImage())
                    .department(user.getDepartment())
                    .major(user.getMajor())
                    .language(user.getLanguage())
                    .build();
            return Header.OK(loginResponse, "Sign in successfully.");
        } catch (Exception e) {
            return Header.ERROR("Wrong id or password.");
        }
    }

}
