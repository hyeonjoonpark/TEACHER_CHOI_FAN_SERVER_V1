package bsm.choi.fancafe.domain.goods.presentation.dto.request;

import bsm.choi.fancafe.domain.goods.Goods;

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
      .build();
  }
}
