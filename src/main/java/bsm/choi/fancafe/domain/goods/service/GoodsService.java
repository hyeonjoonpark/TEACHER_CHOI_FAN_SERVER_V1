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
    public void saveGoods(GoodsUploadRequestDto dto) throws GlobalException{
        try {
            String goodsName = dto.getGoodsName();

            GoodsEntity goodsList = goodsRepository.findByGoodsName(goodsName);

            if(goodsList != null) {
                goodsList.setCount(goodsList.getCount() + 1);
            }

            GoodsEntity goods = dto.toEntity();
            goodsRepository.save(goods);
        } catch (GlobalException e) {
            throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
