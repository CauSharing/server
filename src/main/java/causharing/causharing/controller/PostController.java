package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.model.request.CreatePostRequest;
import causharing.causharing.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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
}
