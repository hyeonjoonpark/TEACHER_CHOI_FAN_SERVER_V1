package bsm.choi.fancafe.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "goods")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodsEntity {
    @Id
    @Column(name = "goods_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goodsId;
    @Column(name = "goods_name")
    private String goodsName;
    @Column(name = "price")
    private int price;
    @Column(name = "seller_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserEntity sellerId;
    @Column(name = "goods_date")
    private ZonedDateTime date;

    @Builder
    public GoodsEntity(int goodsId, String goodsName, int price, UserEntity sellerId, ZonedDateTime date) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.price = price;
        this.sellerId = sellerId;
        this.date = date;
    }
}
