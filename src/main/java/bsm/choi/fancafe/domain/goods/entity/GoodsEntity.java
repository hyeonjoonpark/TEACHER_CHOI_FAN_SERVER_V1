package bsm.choi.fancafe.domain.goods.entity;

import bsm.choi.fancafe.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
    private String sellerId;
    @Column(name = "goods_date")
    private LocalDateTime date;
    @Column(name = "goods_count")
    private Long count;

    @Builder
    public GoodsEntity(int goodsId, String goodsName, int price, String sellerId, LocalDateTime date, Long count) {
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
}
