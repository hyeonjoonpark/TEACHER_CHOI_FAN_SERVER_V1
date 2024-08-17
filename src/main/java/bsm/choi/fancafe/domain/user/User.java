package bsm.choi.fancafe.domain.user;

import bsm.choi.fancafe.domain.board.Board;
import bsm.choi.fancafe.domain.comment.Comment;
import bsm.choi.fancafe.domain.goods.Goods;
import bsm.choi.fancafe.domain.user.types.GradeType;
import bsm.choi.fancafe.domain.user.types.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
            unique = true
    )
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Size(max = 12, min = 2)
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Size(min = 6, max = 12, message = "닉네임은 6자 이상 12자 이하여야 합니다")
    private String nickname;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int point;

    private String profileImageName;
    private String profileImagePath;

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
            mappedBy = "commentWriterId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> commentList;

    public void addComment(Comment comment) {
        comment.setCommentWriterId(this);
        this.commentList.add(comment);
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeType gradeType;

    private String fcmToken;

    @Builder
    public User(String email, String password, String name, String nickname, int point, String profileImageName, String profileImagePath, List<Board> boardList, List<Comment> commentList, List<Goods> sellList, Set<RoleType> roles, String fcmToken) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.point = point;
        this.profileImageName = profileImageName;
        this.profileImagePath = profileImagePath;
        this.boardList = boardList;
        this.commentList = commentList;
        this.sellList = sellList;
        this.roles = roles;
        this.gradeType = GradeType.NEW;
        this.fcmToken = fcmToken;
    }

    public void updateProfile(String email, String password, String profileImageName, String profileImagePath, String name, String nickname) {
        this.email = email;
        this.password = password;
        this.profileImageName = profileImageName;
        this.profileImagePath = profileImagePath;
        this.name = name;
        this.nickname = nickname;
    }
}
