package bsm.choi.fancafe.domain.user;

import bsm.choi.fancafe.domain.auth.RefreshToken;
import bsm.choi.fancafe.domain.board.Board;
import bsm.choi.fancafe.domain.goods.Goods;
import bsm.choi.fancafe.domain.user.types.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class User {
    @Id
    @Column(
            name = "user_id",
            updatable = false,
            unique = true,
            nullable = false
    )
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Email
    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    @Size(max = 10, min = 3)
    @Column(nullable = false)
    private String nickname;

    @Column(
            name = "profile_image",
            columnDefinition = "LONGBLOB"
    )
    private String profileImage;

    @OneToMany(
            mappedBy = "writer", // boardId랑 매핑
            cascade = CascadeType.ALL,
            orphanRemoval = true // User 객체 삭제시 Board 객체도 삭제
    )
    private List<Board> boardList;

    public void addBoard(Board board) {
        board.setWriter(this);
        this.boardList.add(board);
    }

    @OneToMany(
            mappedBy = "seller",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Goods> sellList;

    public void addGoods(Goods goods) {
        goods.setSeller(this);
        this.sellList.add(goods);
    }

    // RoleType 열거형의 컬렉션이 user_roles 테이블에 저장
    // 엔티티의 컬렉션 필드가 기본 엔티티와 별도로 저장됨
    @ElementCollection(targetClass = RoleType.class)
    // user_roles 테이블이 user_id 컬럼을 통해 User 엔티티와 조인됨
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<RoleType> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private RefreshToken refreshToken;


    @Builder
    public User(String email, String password, String nickname, String profileImage, List<Board> boardList, List<Goods> sellList, Set<RoleType> roles, RefreshToken refreshToken) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.boardList = boardList;
        this.sellList = sellList;
        this.roles = roles;
        this.refreshToken = refreshToken;
    }
}
