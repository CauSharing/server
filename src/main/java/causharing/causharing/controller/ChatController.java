package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.model.entity.Chat;
import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.ChatRepository;
import causharing.causharing.model.repository.MatchingRoomRepository;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.ChatMessage;
import causharing.causharing.model.response.ChatResponse;
import causharing.causharing.model.response.CommentListResponse;
import causharing.causharing.service.ChatService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;


    @Autowired
    UserRepository userRepository;
    @Autowired
    MatchingRoomRepository matchingRoomRepository;
    @Autowired
    ChatRepository chatRepository;

    @Autowired
    ChatService chatService;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {

//message,matchingroom, email
            message.setTime(LocalDateTime.now());
            User user=userRepository.findByEmail(message.getEmail());

            MatchingRoom matchingRoom= matchingRoomRepository.findByMatchingRoomId(message.getMatchingRoomId());

            message.setNickname(user.getNickname());
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getMatchingRoomId(), message);



            Chat chat = Chat.builder()
                    .message(message.getMessage())
                    .chatDate(message.getTime())
                    .userEmail(user)
                    .matchingRoomId(matchingRoom)
                    .build();

            chatRepository.save(chat);


    }

    @GetMapping("/chatList")
    @ApiOperation(value="?????? ??????",notes="")
    public Header<List<ChatResponse>> chatList(Long matchingRoomId)
    {
        try {
            return Header.OK(chatService.chatList(matchingRoomId),"chatlist for matchingRoomId "+matchingRoomId);
        }
        catch (Exception e)
        {
            return Header.ERROR("commentList error: "+e);
        }
    }




}