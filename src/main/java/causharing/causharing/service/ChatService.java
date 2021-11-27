package causharing.causharing.service;

import causharing.causharing.model.entity.Chat;
import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.repository.ChatRepository;
import causharing.causharing.model.repository.MatchingRoomRepository;
import causharing.causharing.model.response.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    MatchingRoomRepository matchingRoomRepository;

    @Autowired
    ChatRepository chatRepository;

    public List<ChatResponse> chatList(Long matchingRoomId) {

        MatchingRoom room=matchingRoomRepository.findByMatchingRoomId(matchingRoomId);

        List<Chat> list= chatRepository.findAllByMatchingRoomIdOrderByChatDateAsc(room);



        List<ChatResponse> response= new ArrayList<>();


        for(Chat c:list)
        {
            response.add(ChatResponse.builder()
                            .chatDate(c.getChatDate())
                            .email(c.getUserEmail().getEmail())
                            .image(c.getUserEmail().getImage())
                            .message(c.getMessage())
                            .nickname(c.getUserEmail().getNickname())

                    .build());
        }

        return response;



    }

}
