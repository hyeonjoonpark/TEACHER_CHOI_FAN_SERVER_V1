package bsm.choi.fancafe.domain.goods.presentation.dto.request;

import bsm.choi.fancafe.domain.goods.Goods;
import bsm.choi.fancafe.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsUploadRequestDto {
  private String goodsName;
  private int price;
  private User sellerId;

  public Goods toEntity() {
    return Goods.builder()
      .goodsName(goodsName)
      .price(price)
      .count(1L)
      .sellerId(sellerId)
      .date(LocalDateTime.now())
      .build();
  }
}
