package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.model.response.UserProfileRequest;
import causharing.causharing.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{email}")
    public Header read(@PathVariable String email) {
        return Header.OK(userService.read(email), "send user profile");
    }

    @ApiOperation(value = "수정 시 현재 로그인한 이메일과 프로필 보여주는 이메일이 다를 시 수정 버튼을 만들지 않음.")
    @PutMapping("/profile/update")
    public Header update(@RequestBody
                             @ApiParam(value = "수정 내용 (닉네임, 부서, 학과, 이미지, 언어)" ,required = true)
                                     UserProfileRequest userProfileRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(userService.update(email, userProfileRequest), "Profile has been modified");
        }
        catch (Exception e) {
            return Header.ERROR("Need to login for modifying prifile");
        }
    }
}
