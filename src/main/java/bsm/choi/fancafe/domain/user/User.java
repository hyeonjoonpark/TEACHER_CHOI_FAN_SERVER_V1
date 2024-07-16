package bsm.choi.fancafe.domain.user;

import bsm.choi.fancafe.domain.board.Board;
import bsm.choi.fancafe.domain.goods.Goods;
import bsm.choi.fancafe.domain.user.types.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;
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
  private String email;
  private String password;

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

  private String refToken;

  @Enumerated(EnumType.STRING)
  private RoleType role;

  @Builder
  public User(UUID uuid, String email, String password, String profileImage, List<Board> boardList, List<Goods> sellList, String refToken, RoleType role) {
    this.uuid = uuid;
    this.email = email;
    this.password = password;
    this.profileImage = profileImage;
    this.boardList = boardList;
    this.sellList = sellList;
    this.refToken = refToken;
    this.role = role;
  }
}
