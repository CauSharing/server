package causharing.causharing.service;


import causharing.causharing.model.entity.Comment;
import causharing.causharing.model.entity.Post;
import causharing.causharing.model.repository.CommentRepository;
import causharing.causharing.model.repository.PostRepository;
import causharing.causharing.model.request.ChangecCommentRequest;
import causharing.causharing.model.request.CommentRequest;
import causharing.causharing.model.response.CommentListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;


    public String create(CommentRequest commentRequest) {

        Post post= postRepository.findByPostId(commentRequest.getPostId());

        Comment parentComment= commentRepository.findByCommentId(commentRequest.getParentCommentId());

        Comment comment= Comment.builder()
                                .parentCommentId(parentComment)
                                .commentDate(LocalDateTime.now())
                                .postId(post)
                                .content(commentRequest.getContent())
                                .build();

        commentRepository.save(comment);

        return "Succefully comment inserted";
    }

    public List<CommentListResponse> commentList(Long postId) {

        Post post= postRepository.findByPostId(postId);

        //포스트의 댓글 가져오기
        List<Comment> list= commentRepository.findCommentsByPostIdOrderByCommentIdAsc(post);


        System.out.println(list);
        List<CommentListResponse> response= new ArrayList<>();


        if(!list.isEmpty()) {
            //대댓글 정리
            for (Comment c : list) {

                //댓글
                if (c.getParentCommentId() == null) {
                    response.add(CommentListResponse.builder()
                            .commentId(c.getCommentId())
                            .content(c.getContent())
                            .commentDate(c.getCommentDate())
                            .build());
                } else {//대댓글

                    for (CommentListResponse r : response) {

                        //부모 댓글 찾기
                        if (r.getCommentId() == c.getParentCommentId().getCommentId()) {
                            CommentListResponse clr = CommentListResponse.builder()
                                    .commentId(c.getCommentId())
                                    .content(c.getContent())
                                    .commentDate(c.getCommentDate())
                                    .build();
                            //부모 댓글에 삽입
                            if(r.getChildComment()!=null) {
                                List<CommentListResponse> tmp = r.getChildComment();
                                tmp.add(clr);
                                r.setChildComment(tmp);
                            }
                            else{
                                List<CommentListResponse> tmp=new ArrayList<>();
                                tmp.add(clr);
                                r.setChildComment(tmp);
                            }
                        }

                    }
                }
            }
        }


        return response;



    }

    public String delete(Long commentId) {

        Comment comment= commentRepository.findByCommentId(commentId);


        if(comment.getParentCommentId()==null) { //댓글이면 내용변경
            comment.setContent("삭제된 댓글입니다.");

            commentRepository.save(comment);
        }
        else{//대댓글이면 완전히 삭제
            commentRepository.delete(comment);
        }

        return "sucessfully delete commnet";
    }

    public String update(ChangecCommentRequest changecCommentRequest) {

        Comment comment=commentRepository.findByCommentId(changecCommentRequest.getCommentId());

        comment.setContent(changecCommentRequest.getChangeContent());
        comment.setCommentDate(LocalDateTime.now());

        commentRepository.save(comment);

        return "Successfully Change comment content";
    }
}