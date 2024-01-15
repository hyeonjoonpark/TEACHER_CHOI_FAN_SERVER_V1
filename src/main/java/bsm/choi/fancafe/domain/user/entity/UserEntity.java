package bsm.choi.fancafe.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users")
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Lob
    @Column(name = "profile_image")
    private byte[] profileImage;
    @Column(name = "ref_token")
    private String refToken;
    @Column(name = "is_admin")
    private byte isAdmin;

    @Builder
    public UserEntity(String id, String email, String password, byte[] profileImage, String refToken, byte isAdmin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.refToken = refToken;
        this.isAdmin = isAdmin;
    }
}
