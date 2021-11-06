package causharing.causharing.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {
    private String email;

    private String nickname;

    private String department;

    private String major;

    private String image;

    private String language;
}
