package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.model.entity.User;
import causharing.causharing.model.request.RegisterApiRequest;
import causharing.causharing.model.request.UserApiRequest;
import causharing.causharing.service.EmailService;
import causharing.causharing.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import javax.mail.MessagingException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class RegisterApiController {

    @Autowired
    private EmailService emailService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "이메일 코드 전송",notes = "이메일 코드 전송")
    @GetMapping("/email")
    public Header email(@ApiParam(value = "이메일주소", required = true, example = "test") @RequestParam String email,
                        //@RequestBody Header<AccountApiRequest> request,
                        @ApiIgnore() HttpSession httpSession)
            throws UnsupportedEncodingException, MessagingException {

        UserApiRequest body = new UserApiRequest();

        if(!userService.emailCheck(email)){
            return Header.ERROR("이미 존재하는 Email 입니다.");
        }

        body.setEmail(email);


        //메일보내고 인증코드 받아서
        String randomCode = emailService.sendVerificationEmail(email);

        //인증코드는 받은 UserDto에 저장하고
        body.setVerificationCode(randomCode);

        //세션에 받은 이메일을 key로 UserDTO 객체 Session의 저장
        //세션 만료 시간 3600
        httpSession.setAttribute(body.getEmail(),body);


        return Header.OK("인증 코드가 발송 되었습니다.");

    }

    @ApiOperation(value = "이메일 코드 인증",notes = "이메일 코드 인증")
    @GetMapping("/verify")
    public Header verify(@ApiParam(value = "이메일주소" ,required = true, example = "test") @RequestParam String email,
                         @ApiParam(value = "이메일 인증코드",required = true) @RequestParam String code,
                         @ApiIgnore() HttpSession httpSession){


        UserApiRequest newBody = new UserApiRequest();
        newBody.setEmail(email);
        newBody.setVerificationCode(code);

        //쿠키의 맞는 세션을 받아 해당 세션에서 파라미터로 받은 이메일의 해당하는 ACCOUNT객체 꺼내고
        //해당 객체의 코드와 파라미터로 받은 accountDto의 code를 비교
        UserApiRequest originBody = (UserApiRequest) httpSession.getAttribute(newBody.getEmail());


        if(originBody == null){ //쿠키가 없는경우
            return Header.ERROR("이메일 인증을 해주세요");

        }

        //파라미터로 받은 newAccount와 기존에있던 originAcountDto code가 같으면
        if(newBody.getVerificationCode().contains(originBody.getVerificationCode())){
            // TODO : 세선 유지 후 인증 된 사용자임을 저장
            originBody.setCheckEmaile(true);
            // 기존과 동일한 session name으로 들어오면 덮어씌어진다.
            httpSession.setAttribute(originBody.getEmail(),originBody);

            return Header.OK("이메일 인증 되었습니다.");

        } else{ // 다르면

            return Header.ERROR("인증번호가 틀렸습니다.");

        }
    }


    @ApiOperation(value = "인증 후 회원가입",notes = "필수 정보:email ,password,confirmPw,nickname,department,major,language")
    @PostMapping("/register")
    public Header create(@RequestBody RegisterApiRequest request,
                         @ApiIgnore() HttpSession httpSession) {

        //입력받은 객체에 대한 값을 세션에서 꺼내서
        UserApiRequest origiBody = (UserApiRequest) httpSession.getAttribute(request.getEmail());
    
        if(origiBody==null)
            return Header.ERROR("이메일 인증을 진행하세요");
        //세션에서 꺼낸 originBody가 인증된 사용자인지 검토
        if(origiBody.isCheckEmaile()){


            if(!userService.nicknameCheck(request.getNickname())){
                return Header.ERROR("이미 존재하는 닉네임입니다.");
            }


            // 가입완료
            User user = userService.create(request);

            // 세션만료
            httpSession.removeAttribute(origiBody.getEmail());


            return Header.OK("회원가입이 성공적으로 완료되었습니다.");

        } else{

            return Header.ERROR("인증이 안된 사용자입니다.");
        }
    }


}
