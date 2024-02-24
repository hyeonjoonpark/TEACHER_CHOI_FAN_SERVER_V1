package bsm.choi.fancafe.domain.goods.presentation.dto.response;

import bsm.choi.fancafe.domain.goods.Goods;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GoodsResponseDto {
  private String goodsName;
  private int price;
  private Long goodsCount;
  private LocalDateTime uploadDate;
  private String sellerId;

  public GoodsResponseDto(Goods goods) {
    this.goodsName = goods.getGoodsName();
    this.price = goods.getPrice();
    this.goodsCount = goods.getCount();
    this.uploadDate = goods.getDate();
    this.sellerId = goods.getSeller().getId();
  }
}
