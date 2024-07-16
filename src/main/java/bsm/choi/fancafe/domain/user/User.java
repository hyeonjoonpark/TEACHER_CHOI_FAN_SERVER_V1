package bsm.choi.fancafe.domain.user;

import bsm.choi.fancafe.domain.auth.RefreshToken;
import bsm.choi.fancafe.domain.board.Board;
import bsm.choi.fancafe.domain.goods.Goods;
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
  @Column(name = "user_id")
  @GeneratedValue
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

  @ElementCollection(targetClass = RoleType.class)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Set<RoleType> roles = new HashSet<>();

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private RefreshToken refreshToken;


  @Builder
  public User(UUID uuid, String email, String password, String nickname, String profileImage, List<Board> boardList, List<Goods> sellList, Set<RoleType> roles, RefreshToken refreshToken) {
    this.uuid = uuid;
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
