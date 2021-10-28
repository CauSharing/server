package causharing.causharing.service;

import causharing.causharing.model.entity.Invitation;
import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.InvitationRepository;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.InviteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;

    public String invite(String email, InviteRequest inviteRequest) {
        User user = userRepository.findTop1ByDepartmentAndMajorAndLanguageOrderByMatchingCountAsc(
                inviteRequest.getCollege(),
                inviteRequest.getMajor(),
                inviteRequest.getLanguage());

        if (user==null) {
            return "조건에 부합하는 학생이 존재하지 않습니다.";
        }
        else {
            Invitation invitation = Invitation.builder()
                    .InvitePerson(email)
                    .InvitedPerson(user.getEmail())
                    .build();
            invitationRepository.save(invitation);
            return user.getEmail() + "에게 요청을 보냈습니다.";
        }
    }
}
