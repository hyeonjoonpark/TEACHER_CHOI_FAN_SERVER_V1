package bsm.choi.fancafe.domain.goods.presentation.dto.request;

import bsm.choi.fancafe.domain.goods.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record GoodsUploadRequest(
  String goodsName,
  int price,
  String sellerId
) {
  public Goods toEntity() {
    return Goods.builder()
      .goodsName(goodsName)
      .price(price)
      .count(1L)
      .date(LocalDateTime.now())
      .build();
  }
}
