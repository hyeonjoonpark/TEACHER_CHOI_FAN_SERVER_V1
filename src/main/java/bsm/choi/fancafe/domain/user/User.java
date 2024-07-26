package bsm.choi.fancafe.domain.user;

import bsm.choi.fancafe.domain.auth.RefreshToken;
import bsm.choi.fancafe.domain.board.Board;
import bsm.choi.fancafe.domain.comment.Comment;
import bsm.choi.fancafe.domain.goods.Goods;
import bsm.choi.fancafe.domain.user.types.GradeType;
import bsm.choi.fancafe.domain.user.types.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

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

    @Size(max = 12, min = 3)
    @Column(nullable = false)
    private String name;

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private RefreshToken refreshToken;


    @Builder
    public User(String email, String password, String name, String profileImage, List<Board> boardList, List<Goods> sellList, RefreshToken refreshToken) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.boardList = boardList;
        this.sellList = sellList;
        this.roles.add(RoleType.ROLE_USER); // User 생성 시 자동으로 user_roles 테이블에 저장
        this.gradeType = GradeType.NEW;
        this.refreshToken = refreshToken;
    }
}
