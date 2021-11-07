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
            return Header.OK(commentService.create(commentRequest));
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
    @ApiOperation(value="댓글 삭제",notes="댓글이 삭제되면 '삭제된 메시지입니다'라고 내용만 변경, 대댓글일경우 완전히 삭제")
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
            return Header.OK(commentService.update(changecCommentRequest),"");
        }
        catch (Exception e)
        {
            return Header.ERROR("update comment error: "+e);
        }
    }


}
