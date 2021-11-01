package causharing.causharing.service;

import causharing.causharing.model.entity.Invitation;
import causharing.causharing.model.entity.Matching;
import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.InvitationRepository;
import causharing.causharing.model.repository.MatchingRepository;
import causharing.causharing.model.repository.MatchingRoomRepository;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.InvitationList;
import causharing.causharing.model.request.InviteRequest;
import causharing.causharing.model.request.MatchingAcceptRequest;
import causharing.causharing.model.request.MatchingRejectRequest;
import causharing.causharing.model.response.InvitationListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchingService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchingRepository matchingRepository;

    @Autowired
    private MatchingRoomRepository matchingRoomRepository;

    public String invite(String email, InviteRequest inviteRequest) {
        User user = userRepository.findTop1ByDepartmentAndMajorAndLanguageAndEmailNotOrderByMatchingCountAsc(
                inviteRequest.getCollege(),
                inviteRequest.getMajor(),
                inviteRequest.getLanguage(),
                email);

        // 자기 자신한테 초대되는 현상 막기 -> clear EmailNot 추가
        // TODO: 같은사람한테 초대되는 현상 막기
        // sender와 receiver가 같은 방을 가지고 있는 receiver에게는 초대하면 안됨

        if (user==null) {
            return "There is no student who meets condition.";
        }
        else {
            Invitation invitation = Invitation.builder()
                    .InvitePerson(email)
                    .InvitedPerson(user.getEmail())
                    .build();
            invitationRepository.save(invitation);
            return  "send inviting message to"+user.getNickname() ;
        }
    }

    //send inviting message to
    public InvitationListResponse inviteList(String email) {
        List<Invitation> list = invitationRepository.findByInvitedPerson(email);
        List<InvitationList> invitationList = list.stream().map(invitation -> response(invitation))
                .collect(Collectors.toList());
        return InvitationListResponse.builder()
                .invitationList(invitationList)
                .build();
    }

    private InvitationList response(Invitation invitation) {
        return InvitationList.builder()
                .invitePerson(invitation.getInvitePerson())
                .build();
    }

    public String accept(MatchingAcceptRequest matchingAcceptRequest, String receiverEmail) {
        User sender = userRepository.findByEmail(matchingAcceptRequest.getSender());
        User receiver = userRepository.findByEmail(receiverEmail);
        // 매칭 룸 생성
        MatchingRoom matchingRoom = MatchingRoom.builder().build();
        matchingRoomRepository.save(matchingRoom);

        // receiver 수락 시 sender 매칭 생성
        Matching senderMatching = Matching.builder()
                .matchingRoomId(matchingRoom)
                .user(sender)
                .build();

        // receiver 수락 시 receiver 매칭 생성
        Matching receiverMatching = Matching.builder()
                .matchingRoomId(matchingRoom)
                .user(receiver)
                .build();

        matchingRepository.save(senderMatching);
        matchingRepository.save(receiverMatching);

        return "Sucessfully matchingroom created.";
    }

    public String reject(MatchingRejectRequest matchingRejectRequest, String receiver) {
        Invitation invitation = invitationRepository.findByInvitedPersonAndInvitePerson(receiver, matchingRejectRequest.getSender());
        invitationRepository.delete(invitation);
        return "Reject matching";
    }
}
