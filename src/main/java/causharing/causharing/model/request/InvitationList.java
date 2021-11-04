package causharing.causharing.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationList {
    private String invitePerson;

    private String invitePersonImage;

    private String invitePersonNickname;

    private String invitePersonMajor;
}
