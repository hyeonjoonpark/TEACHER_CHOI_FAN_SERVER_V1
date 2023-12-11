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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goodsId;
    private String goodsName;
    private int price;
    private String sellerId;
    private ZonedDateTime date;
}
