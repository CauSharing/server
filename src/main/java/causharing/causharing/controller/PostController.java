package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.model.request.CreatePostRequest;
import causharing.causharing.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/createPost")
    public Header create(@RequestBody CreatePostRequest createPostRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(postService.create(email, createPostRequest), "create successfully");
        }
        catch (Exception e) {
            return Header.ERROR("Need to login for creating a post");
        }
    }

    @GetMapping("/post")
    @ApiOperation(value = "게시물 불러오기, /post?postId=**",notes = "")
    public Header getPost(@RequestParam Long postId) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(postService.getPost(email, postId), "get successfully");
        }
        catch (Exception e) {
            return Header.ERROR("Need to login for see the post");
        }
    }

    @GetMapping("/postList")
    @ApiOperation(value="게시물 목록",notes="매칭룸 아이디, 원하는 날짜= YYYY-MM-DD")
     public Header postList(@RequestParam Long matchingroomId, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate postDate)
    {

        try{

            return Header.OK(postService.getPostList(matchingroomId, postDate),"get postList successfully");

        }catch(Exception e)
        {
            return Header.ERROR("get postList fail: "+e);
        }

    }


    @PutMapping("/postUpdate")
    @ApiOperation(value = "게시물 수정, /postUpdate?postId=** 와 RequestBody",notes = "")
    public Header update(@RequestParam Long postId,
                         @RequestBody CreatePostRequest createPostRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(postService.update(email, postId, createPostRequest), "update the post successfully");
        }
        catch (Exception e) {
            return Header.ERROR("Need to login for updating the post");
        }
    }

    @DeleteMapping("/postDelete")
    @ApiOperation(value = "게시물 삭제, /postDelete?postId=**",notes = "")
    public Header delete(@RequestParam Long postId) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(postService.delete(email, postId), "delete post successfully");
        }
        catch (Exception e) {
            return Header.ERROR("Need to login for delete post");
        }
    }

    @GetMapping("/postDate")
    @ApiOperation(value = "게시물이 있는 날짜 조회")
    public Header read(@RequestParam Long MatchingRoomId,
                       // parameter default value 추가
                       @ApiParam(value = "2021-11-01", type = "LocalDate", required = true, example = "2021-11-01")
                       @RequestParam
                       // LocalDateTime 형식 설정
                       @DateTimeFormat(pattern="yyyy-MM-dd")
                       LocalDate postDate) {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(postService.read(MatchingRoomId, postDate), "read successfully");
        }
        catch (Exception e) {
            return Header.ERROR("Need to login for viewing post");
        }

    }
}
