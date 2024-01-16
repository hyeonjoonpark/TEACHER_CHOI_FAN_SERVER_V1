package bsm.choi.fancafe.domain.goods.presentation.dto.request;

import bsm.choi.fancafe.domain.goods.entity.GoodsEntity;
import bsm.choi.fancafe.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsUploadRequestDto {
    private String goodsName;
    private int price;
    private String sellerId;

    public GoodsEntity toEntity() {
        return GoodsEntity.builder()
                .goodsName(this.goodsName)
                .price(this.price)
                .count(1L)
                .sellerId(sellerId)
                .date(LocalDateTime.now())
                .build();
    }

}
