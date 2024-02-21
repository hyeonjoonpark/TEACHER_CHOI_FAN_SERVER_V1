package bsm.choi.fancafe.domain.goods;

import bsm.choi.fancafe.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "goods")
@Table(name = "goods")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goods {
  @Id
  @Column(name = "goods_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int goodsId;
  @Column(name = "goods_name")
  private String goodsName;
  @Column(name = "price")
  private int price;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User sellerId;

  public void setSellerId(User sellerId) {
    this.sellerId = sellerId;
  }

  @Column(name = "goods_date")
  private LocalDateTime date;
  @Column(name = "goods_count")
  private Long count;

  @Builder
  public Goods(int goodsId, String goodsName, int price, User sellerId, LocalDateTime date, Long count) {
    this.goodsId = goodsId;
    this.goodsName = goodsName;
    this.price = price;
    this.sellerId = sellerId;
    this.date = date;
    this.count = count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  @ManyToOne(optional = false)
  private User users;

  public User getUsers() {
    return users;
  }

  public void setUsers(User users) {
    this.users = users;
  }
}
