package causharing.causharing.service;

import causharing.causharing.model.entity.Invitation;
import causharing.causharing.model.entity.Matching;
import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.InvitationRepository;
import causharing.causharing.model.repository.MatchingRepository;
import causharing.causharing.model.repository.MatchingRoomRepository;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.*;
import causharing.causharing.model.response.InvitationListResponse;
import causharing.causharing.model.response.PossibleInvitationList;
import causharing.causharing.model.response.UserProfileResponse;
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


        User sender= userRepository.findByEmail(email);

        //자신 제외, 매칭할 사람 찾기
        User user = userRepository.findTop1ByDepartmentAndMajorAndLanguageAndEmailNotOrderByMatchingCountAsc(
                inviteRequest.getCollege(),
                inviteRequest.getMajor(),
                inviteRequest.getLanguage(),
                sender.getEmail());
        if (user==null) {
            return "There is no student who meets condition.";
        }

        //이미 매칭된 사람 &  초대된사람 제외
        String tmp = "";
        while (alreadyMatching(sender, user)) {
            // 기존 유저와 현재 찾은 유저 둘다 제외 할 필요있음. -> clear tmp변수에 user들 추가하면서 NotIn으로 거른다.
            tmp += user.getEmail() + " ";
            user = userRepository.findTop1ByDepartmentAndMajorAndLanguageAndEmailNotAndEmailNotInOrderByMatchingCountAsc(
                    inviteRequest.getCollege(),
                    inviteRequest.getMajor(),
                    inviteRequest.getLanguage(),
                    sender.getEmail(),
                    tmp.split(" ")
            );

        }

        if (user==null) {
            return "There is no student who meets condition.";
        }
        else {
            Invitation invitation = Invitation.builder()
                    .InvitePerson(sender.getEmail())
                    .InvitedPerson(user.getEmail())
                    .build();
            invitationRepository.save(invitation);
            return  "send inviting message to "+user.getNickname();
        }
    }

    //초대목록 return
    public InvitationListResponse inviteList(String email) {
        List<Invitation> list = invitationRepository.findByInvitedPerson(email);
        List<InvitationList> invitationList = list.stream().map(invitation -> response(invitation))
                .collect(Collectors.toList());
        return InvitationListResponse.builder()
                .invitationList(invitationList)
                .build();
    }

    private InvitationList response(Invitation invitation) {
        User user = userRepository.findByEmail(invitation.getInvitePerson());
        return InvitationList.builder()
                .invitePerson(invitation.getInvitePerson())
                .invitePersonImage(user.getImage())
                .invitePersonNickname(user.getNickname())
                .invitePersonMajor(user.getMajor())
                .matchingRoomId(invitation.getMatchingRoomId())
                .build();
    }

    //초대수락
    public String accept(MatchingAcceptRequest matchingAcceptRequest, String receiverEmail) {
        User sender = userRepository.findByEmail(matchingAcceptRequest.getSender());
        User receiver = userRepository.findByEmail(receiverEmail);
        // 매칭 룸 생성
        Invitation invitation = invitationRepository.findByInvitedPersonAndInvitePerson(receiverEmail, matchingAcceptRequest.getSender());

        //새로 매칭일경우
        if(matchingAcceptRequest.getMatchingroomId()==0L) {
            MatchingRoom matchingRoom = MatchingRoom.builder().build();
            matchingRoomRepository.save(matchingRoom);
            System.out.println("들어옴");

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

        }
        else{//기존 그룹 매칭일 경우 reciever만 매칭
            MatchingRoom matchingRoom= matchingRoomRepository.findByMatchingRoomId(matchingAcceptRequest.getMatchingroomId());
            Matching receiverMatching = Matching.builder()
                    .matchingRoomId(matchingRoom)
                    .user(receiver)
                    .build();

            matchingRepository.save(receiverMatching);

        }


        //매칭 완료후 invitation 삭제
        invitationRepository.delete(invitation);
        return "Sucessfully matchingroom created.";
    }


    //초대 거절
    public String reject(MatchingRejectRequest matchingRejectRequest, String receiver) {
        Invitation invitation;

        //첫 매칭일 경우
        if(matchingRejectRequest.getMatchingroomId()==0L)
        invitation = invitationRepository.findByInvitedPersonAndInvitePerson(receiver, matchingRejectRequest.getSender());
        else//기존 그룹 초대일 경우
            invitation=invitationRepository.findByInvitePersonAndAndMatchingRoomId(matchingRejectRequest.getSender(), matchingRejectRequest.getMatchingroomId());

        invitationRepository.delete(invitation);
        return "Reject matching";
    }

    //이미 매칭한 사이인지 확인
    //매칭/초대된 사이이면 return true, else return false
    public Boolean alreadyMatching(User sender, User reciever ){

        if(reciever==null)
            return false;
        else{
            List<Matching> list=matchingRepository.findByUser(sender); //sender의 매칭리스트

            //이미 매칭된 사람이면 return true
            for(Matching m: list)
            {

                if(matchingRepository.findByMatchingRoomIdAndUser(m.getMatchingRoomId(), reciever)!=null)
                {
                    return true;
                }
            }

            //이미 초대된 사람이면 return true
            Invitation invitation=invitationRepository.alreadyMatching(sender.getEmail(),reciever.getEmail());
            if(invitation!=null && invitation.getMatchingRoomId()==null)
            {
                return true;
            }

            return false;


        }



    }

    public PossibleInvitationList possibleInvite(Long roomId) {

        MatchingRoom matchingRoom=matchingRoomRepository.findByMatchingRoomId(roomId); //해당 그룹 찾기

        //그 그룹에 속한 user찾기
        List<Matching> matchingList= matchingRepository.findByMatchingRoomId(matchingRoom);
        List<String> existedList=new ArrayList<>();

        for(Matching m: matchingList)
        {
            existedList.add(m.getUser().getEmail());
        }

        //그룹에 속하지 않은 user 찾기
        List<User> possibleUserList= userRepository.findUsersByEmailNotIn(existedList);

        System.out.println(possibleUserList);

        //이미 초대된 경우 제외
        //초대 가능한 user list return
        List<UserProfileResponse> profile= new ArrayList<>();
        for(User u: possibleUserList)
        {
            if(u.getMatchingCount()<3&&(invitationRepository.findByInvitedPersonAndAndMatchingRoomId(u.getEmail(),roomId)==null)) {
                profile.add(UserProfileResponse.builder().image(u.getImage())
                        .language(u.getLanguage())
                        .nickname(u.getNickname())
                        .department(u.getDepartment())
                        .major(u.getMajor())
                        .email(u.getEmail()).build()
                );
            }
        }

        return PossibleInvitationList.builder().possibleProfileList(profile).build();


    }


    //기존의 그룹에 user 선택후 초대
    public String  invitetoexist(String email, InviteToExistRequest inviteToExistRequest) {

        //reciever user 찾기
        User reciever=userRepository.findByEmail(inviteToExistRequest.getReciever());

        //기존의 그룹에 초대함
        Invitation invitation=Invitation.builder().InvitePerson(email)
                                    .InvitedPerson(reciever.getEmail())
                .MatchingRoomId(inviteToExistRequest.getInviteRoomId()).build();

        invitationRepository.save(invitation);

        return "Sucessfully send inviting message to"+ reciever.getNickname();
    }
}
