package causharing.causharing.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
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
public class User implements UserDetails {

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

    private int matchingCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Matching> matchingList1 = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEmail")
    List<Chat> chatList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEmail")
    List<Post> postList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "writer")
    List<Comment> commentList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
