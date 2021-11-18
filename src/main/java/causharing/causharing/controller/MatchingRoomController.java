package causharing.causharing.controller;


import causharing.causharing.model.Header;
import causharing.causharing.model.request.ChangecCommentRequest;
import causharing.causharing.model.request.InviteRequest;
import causharing.causharing.model.request.RoomRequest;
import causharing.causharing.model.request.UserApiRequest;
import causharing.causharing.service.MatchingRoomService;
import causharing.causharing.service.MatchingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
public class MatchingRoomController {

    @Autowired
    private MatchingRoomService matchingRoomService;


    @ApiOperation(value = "사용자의 매칭룸 목록 조회")
    @GetMapping("/roomList")
    @CrossOrigin(origins="*", maxAge=3600)
    public Header selectAllRoomList() {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(matchingRoomService.roomList(email), "매칭룸목록(roomid, roomimage)");
        } catch (Exception e) {
            return Header.ERROR("Need to login for seeing matchingroomList "+e);
        }
    }


    @ApiOperation(value="매칭룸 정보 수정")
    @PostMapping("/updateRoom")
    public Header updateRoom(@RequestBody RoomRequest roomRequest)
    {
        try {

            return Header.OK(matchingRoomService.update(roomRequest),"");
        }
        catch (Exception e)
        {
            return Header.ERROR("update matchingroom error: "+e);
        }
    }

}
