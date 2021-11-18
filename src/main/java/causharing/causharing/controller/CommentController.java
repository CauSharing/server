package causharing.causharing.controller;


import causharing.causharing.model.Header;
import causharing.causharing.model.entity.Comment;
import causharing.causharing.model.request.ChangecCommentRequest;
import causharing.causharing.model.request.CommentRequest;
import causharing.causharing.model.request.InviteRequest;
import causharing.causharing.model.response.CommentListResponse;
import causharing.causharing.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/createComment")
    @ApiOperation(value = "댓글 생성 ", notes = "댓글이면 parentCommnetId=0 else 대댓글이면 댓글의 id필요")
    public Header createComment(@RequestBody CommentRequest commentRequest) {

        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();
            return Header.OK(commentService.create(commentRequest,email));
        }
        catch (Exception e)
        {
             return Header.ERROR("create commnet error: "+e);
        }

    }

    @GetMapping("/commentList")
    @ApiOperation(value="댓글 목록",notes="")
    public Header<List<CommentListResponse>> commentList( Long postId)
    {
        try {
            return Header.OK(commentService.commentList(postId),"commentList for postId "+postId);
        }
        catch (Exception e)
        {
            return Header.ERROR("commentList error: "+e);
        }
    }

    @DeleteMapping("/deleteComment")
    @ApiOperation(value="댓글 삭제",notes="댓글:1) 대댓글이 있으면 '삭제된 댓글입니다' 내용변경, 2) 대댓글이 없으면 삭제/" +
            "대댓글:1)댓글이 존재하면 대댓글만 삭제 2) 댓글이 존재x일때 2-1) 다른 대댓글이 있으면 대댓글만 삭제 2-2) 다른 대댓글이 없으면 " +
            "댓글과 대댓글 같이 삭제")
    public Header deleteComment( Long commentId)
    {
        try {
            return Header.OK(commentService.delete(commentId),"");
        }
        catch (Exception e)
        {
            return Header.ERROR("delete comment error: "+e);
        }
    }

    @PutMapping("/updateComment")
    @ApiOperation(value="댓글 수정",notes="")
    public Header updateComment(@RequestBody ChangecCommentRequest changecCommentRequest)
    {
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();
            return Header.OK(commentService.update(changecCommentRequest,email),"");
        }
        catch (Exception e)
        {
            return Header.ERROR("update comment error: "+e);
        }
    }


}
