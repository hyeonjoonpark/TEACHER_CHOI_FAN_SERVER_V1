package bsm.choi.fancafe.domain.goods.service;

import bsm.choi.fancafe.domain.goods.entity.GoodsEntity;
import bsm.choi.fancafe.domain.goods.presentation.dto.request.GoodsUploadRequestDto;
import bsm.choi.fancafe.domain.goods.repository.GoodsRepository;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    @Transactional(readOnly = true)
    public List<GoodsEntity> getList() throws GlobalException {
        try {
            List<GoodsEntity> result = goodsRepository.findAll();
            return result;
        } catch (GlobalException e) {
            throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    public void saveGoods(GoodsUploadRequestDto dto) throws GlobalException {
        try {
            String goodsName = dto.getGoodsName();
            int goodsPrice = dto.getPrice();

            GoodsEntity existingGoods = goodsRepository.findByGoodsNameAndPrice(goodsName, goodsPrice);

            if (existingGoods != null) {
                // 이미 동일한 상품이 존재하는 경우, 수량을 증가시킵니다.
                existingGoods.setCount(existingGoods.getCount() + 1);
                goodsRepository.save(existingGoods);
                System.out.println("수량 증가 완료");
            } else {
                // 동일한 상품이 존재하지 않는 경우, 새로운 상품을 저장합니다.
                GoodsEntity goods = dto.toEntity();
                goodsRepository.save(goods);
                System.out.println("저장 완료");
            }
        } catch (GlobalException e) {
            throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

}
