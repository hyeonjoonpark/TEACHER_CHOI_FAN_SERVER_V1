package bsm.choi.fancafe.domain.goods.presentation.dto.response;

import lombok.Builder;

@Builder
public record GoodsResponseDto(
        String goodsName,
        int price,
        Long goodsCount,
        String sellerId
) {

}
