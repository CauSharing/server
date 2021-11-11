package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.model.request.LoginRequest;
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

    @ApiOperation(value = "로그인 페이지", notes = "JWT 토큰을 전달")
    @PostMapping("/login")
    @CrossOrigin(origins="*", maxAge=3600)
    public Header<String> generateToken(
            @ApiParam(value = "!!이메일 주소, 비밀번호 필수!!", required = true, example = "email:test@naver.com, password:****")
            @RequestBody LoginRequest loginRequest
    ) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            // TODO : 유저 정보 같이 보내기.
            return Header.OK(jwtUtil.generateToken(loginRequest.getEmail()), "로그인 되었습니다.");
        } catch (Exception e) {
            return Header.ERROR("잘못된 아이디 혹은 비밀번호 입니다.");
        }
    }

}
