package bsm.choi.fancafe.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
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
    @Column(name = "acc_token")
    private String accToken;
    @Column(name = "ref_token")
    private String refToken;
    @Column(name = "is_admin")
    private byte isAdmin;
}
