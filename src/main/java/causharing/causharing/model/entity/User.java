package causharing.causharing.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
@ToString(exclude = {"user", "userEmail"})
public class User {

    @Id
    private String email;

    private String password;

    private String nickname;

    private String department;

    private String major;

    private String image;

    private String language;

    private int reporter_count;

    private int reported_count;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Matching> matchingList1 = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEmail")
    List<Chat> chatList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEmail")
    List<Post> postList = new ArrayList<>();
}
