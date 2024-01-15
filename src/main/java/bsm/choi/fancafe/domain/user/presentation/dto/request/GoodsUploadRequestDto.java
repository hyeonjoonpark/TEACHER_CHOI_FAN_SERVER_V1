package bsm.choi.fancafe.domain.user.presentation.dto.request;

import bsm.choi.fancafe.domain.user.entity.GoodsEntity;
import bsm.choi.fancafe.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsUploadRequestDto {
    private String goodsName;
    private int price;
    private UserEntity sellerId;
    private ZonedDateTime date;

    public GoodsEntity toEntity() {
        return GoodsEntity.builder()
                .goodsName(this.goodsName)
                .price(this.price)
                .sellerId(this.sellerId)
                .build();
    }

}
