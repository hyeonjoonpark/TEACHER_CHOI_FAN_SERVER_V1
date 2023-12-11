package bsm.choi.fancafe.domain.user.presentation.dto.request;

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
    private String sellerId;
    private ZonedDateTime date;
}
