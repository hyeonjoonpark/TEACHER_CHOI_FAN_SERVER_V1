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
  @JoinColumn(name = "seller")
  private User seller;

  public void setSeller(User seller) {
    this.seller = seller;
  }

  @Column(name = "goods_date")
  private LocalDateTime date;
  @Column(name = "goods_count")
  private Long count;

  public void setCount(Long count) {
    this.count = count;
  }


  @Builder
  public Goods(int goodsId, String goodsName, int price, User seller, LocalDateTime date, Long count) {
    this.goodsId = goodsId;
    this.goodsName = goodsName;
    this.price = price;
    this.seller = seller;
    this.date = date;
    this.count = count;
  }
}
