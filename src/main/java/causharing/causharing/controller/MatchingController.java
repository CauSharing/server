package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.model.request.InvitationList;
import causharing.causharing.model.request.InviteRequest;
import causharing.causharing.model.response.InvitationListResponse;
import causharing.causharing.service.MatchingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class MatchingController {

    @Autowired
    private MatchingService matchingService;

    @PostMapping("/matching")
    @ApiOperation(value = "매칭 초대하는 페이지", notes = "")
    public Header invite(@RequestBody InviteRequest inviteRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(matchingService.invite(email, inviteRequest));
        }
        catch (Exception e) {
            return Header.ERROR("매칭을 하기 위해선 로그인이 필요합니다!");
        }
    }

    @GetMapping("/matchingList")
    @ApiOperation(value = "초대 목록을 보는 페이지", notes = "")
    public Header<InvitationListResponse> inviteList() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(matchingService.inviteList(email),"");
        }
        catch (Exception e) {
            return Header.ERROR("초대 목록을 보기 위해선 로그인이 필요합니다!");
        }
    }




}
