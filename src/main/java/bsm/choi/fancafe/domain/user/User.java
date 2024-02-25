package bsm.choi.fancafe.domain.user;

import bsm.choi.fancafe.domain.board.Board;
import bsm.choi.fancafe.domain.goods.Goods;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class User {
  @Id
  @Column(name = "user_id")
  private String id;
  @Column(name = "email")
  private String email;
  @Column(name = "password")
  private String password;
  @Column(
    name = "profile_image",
    columnDefinition = "LONGBLOB"
  )
  private String profileImage;

  @OneToMany(
    mappedBy = "writer", // boardId랑 매핑
    cascade = CascadeType.ALL,
    // default : fetch: FetchType.LAZY
    orphanRemoval = true // User 객체 삭제시 Board 객체도 삭제
  )
  @JsonManagedReference
  private List<Board> boardList;

  public void addBoard(Board board) {
    board.setWriter(this);
    this.boardList.add(board);
  }

  @OneToMany(
    mappedBy = "seller",
    cascade = CascadeType.ALL,
    // default : fetch: FetchType.LAZY
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
  public User(String id, String email, String password, String profileImage, List<Board> boardList, List<Goods> sellList, String refToken, RoleType role) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.profileImage = profileImage;
    this.boardList = boardList;
    this.sellList = sellList;
    this.refToken = refToken;
    this.role = role;
  }
}
