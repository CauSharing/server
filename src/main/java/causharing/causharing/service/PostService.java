package causharing.causharing.service;

import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.entity.Post;
import causharing.causharing.model.repository.MatchingRoomRepository;
import causharing.causharing.model.repository.PostRepository;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.CreatePostRequest;
import causharing.causharing.model.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .userEmail(post.getUserEmail().getEmail())
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

    public List<PostResponse> getPostList(Long matchingroomId, LocalDate postDate) {


        MatchingRoom matchingRoom= matchingRoomRepository.findByMatchingRoomId(matchingroomId);



        List<Post> list= postRepository.findPostsByMatchingRoomIdAndPostDateBetween(matchingRoom, postDate.atStartOfDay(), postDate.plusDays(1).atStartOfDay());

        List<PostResponse> response= new ArrayList<>();
        if(!list.isEmpty()) {
            for (Post p : list) {
                

                response.add(PostResponse.builder()
                        .postId(p.getPostId())
                        .postDate(p.getPostDate())
                        .content(p.getContent())
                        .title(p.getTitle())
                        .matchingRoomId(p.getMatchingRoomId().getMatchingRoomId())
                        .userEmail(p.getUserEmail().getEmail())
                        .userNickname(p.getUserEmail().getNickname())
                        .build());
            }
        }


        return response;


    }

    public List<String> read(Long matchingRoomId, LocalDate month) {
        MatchingRoom matchingRoom = matchingRoomRepository.findByMatchingRoomId(matchingRoomId);
        List<Post> posts = postRepository.findPostsByMatchingRoomIdAndPostDateBetween(matchingRoom, month.atStartOfDay(), month.plusDays(31).atStartOfDay());
        List<String> postsDate = posts.stream().map(post -> responsePostDate(post))
                .collect(Collectors.toList());

        return postsDate;

    }

    private String responsePostDate(Post post) {
        return post.getPostDate().toString().substring(0, 10);
    }
}
