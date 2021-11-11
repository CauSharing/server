package causharing.causharing.service;

import causharing.causharing.model.entity.Post;
import causharing.causharing.model.repository.MatchingRoomRepository;
import causharing.causharing.model.repository.PostRepository;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.CreatePostRequest;
import causharing.causharing.model.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchingRoomRepository matchingRoomRepository;

    @Autowired
    private PostRepository postRepository;

    /**
     * 게시물 생성 API
     */
    public PostResponse create(String email, CreatePostRequest createPostRequest) {

        Post post = Post.builder()
                .title(createPostRequest.getTitle())
                .postDate(createPostRequest.getPostDate())
                .content(createPostRequest.getContent())
                .matchingRoomId(matchingRoomRepository.findByMatchingRoomId(createPostRequest.getMatchingRoomId()))
                .userEmail(userRepository.findByEmail(email))
                .build();

        postRepository.save(post);


        return PostResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .postDate(post.getPostDate())
                .content(post.getContent())
                .matchingRoomId(post.getMatchingRoomId().getMatchingRoomId())
                .userNickname(post.getUserEmail().getNickname())
                .build();

    }

    /**
     * 게시물 확인 API
     */
    public PostResponse getPost(String email, Long postId) {
        Post post = postRepository.findByPostId(postId);
        return PostResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .postDate(post.getPostDate())
                .content(post.getContent())
                .matchingRoomId(post.getMatchingRoomId().getMatchingRoomId())
                .userNickname(post.getUserEmail().getNickname())
                .build();
    }

    public PostResponse update(String email, Long postId, CreatePostRequest createPostRequest) {
        Post post = postRepository.findByPostId(postId);

        post.setTitle(createPostRequest.getTitle())
                .setPostDate(createPostRequest.getPostDate())
                .setContent(createPostRequest.getContent())
                ;

        postRepository.save(post);

        return PostResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .postDate(post.getPostDate())
                .content(post.getContent())
                .matchingRoomId(post.getMatchingRoomId().getMatchingRoomId())
                .userNickname(post.getUserEmail().getNickname())
                .build();
    }

    public String delete(String email, Long postId) {
        Post post = postRepository.findByPostId(postId);
        postRepository.delete(post);
        return "Delete post successfully";
    }
}
