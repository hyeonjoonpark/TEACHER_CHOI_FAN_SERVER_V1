package bsm.choi.fancafe.domain.goods;

import bsm.choi.fancafe.domain.goods.type.EventType;
import bsm.choi.fancafe.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity(name = "goods")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Goods {
    @Id
    @Column(name = "goods_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsId;
    private String goodsName;
    private int price;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @CreatedBy
    private User seller;

    @Setter
    @Column(name = "goods_count")
    private Long count;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Builder
    public Goods(String goodsName, int price, Long count, EventType eventType) {
        this.goodsName = goodsName;
        this.price = price;
        this.count = count;
        this.eventType = eventType;
    }
}
