package causharing.causharing.service;


import causharing.causharing.model.entity.Matching;
import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.MatchingRepository;
import causharing.causharing.model.repository.MatchingRoomRepository;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.response.RoomListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchingRoomService {

    @Autowired
    MatchingRoomRepository matchingRoomRepository;

    @Autowired
    MatchingRepository matchingRepository;

    @Autowired
    UserRepository userRepository;

    //사용자의 매칭룸 목록 가져오기
    public List<RoomListResponse> roomList(String email) {

        User user=userRepository.getById(email);
        List<Matching> matchingList= matchingRepository.findByUser(user);
        List<RoomListResponse> roomList=new ArrayList<>();

        for(Matching m:matchingList)
        {
            MatchingRoom r=m.getMatchingRoomId();
            roomList.add(RoomListResponse.builder()
                    .matchingRoomId(r.getMatchingRoomId())
                    .matchingRoomImage(r.getMatchingRoomImage())
                    .build());
        }


        //System.out.println(matchingList.size());
        return roomList;
    }
}