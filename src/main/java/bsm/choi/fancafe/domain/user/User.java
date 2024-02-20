package bsm.choi.fancafe.domain.user;

import bsm.choi.fancafe.domain.board.Board;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user")
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
    mappedBy = "boardId", // boardId랑 매핑
    cascade = CascadeType.ALL,
    orphanRemoval = true // User 객체 삭제시 Board 객체도 삭제
  )
  private List<Board> boardList;
  public void addBoard(Board board) {
    board.setWriter(this);
    this.boardList.add(board);
  }


  @Column(name = "ref_token")
  private String refToken;
  @Column(name = "is_admin")
  private byte isAdmin;

  @Builder
  public User(String id, String email, String password, String profileImage, List<Board> boardList, String refToken, byte isAdmin) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.profileImage = profileImage;
    this.boardList = boardList;
    this.refToken = refToken;
    this.isAdmin = isAdmin;
  }
}
