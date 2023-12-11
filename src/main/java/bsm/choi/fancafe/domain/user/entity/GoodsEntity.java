package bsm.choi.fancafe.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Entity
@Table(name = "goods")
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private String sellerId;
    @Column(name = "goods_date")
    private ZonedDateTime date;
}
