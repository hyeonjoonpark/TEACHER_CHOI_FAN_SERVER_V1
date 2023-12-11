package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.entity.GoodsEntity;
import bsm.choi.fancafe.domain.user.presentation.dto.request.GoodsUploadRequestDto;
import bsm.choi.fancafe.domain.user.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    public List<GoodsEntity> goodsList() {
        List<GoodsEntity> result = goodsRepository.findAll();
        return result;
    }

    public void upload(GoodsUploadRequestDto dto) {
        try {
            GoodsEntity goodsEntity = new GoodsEntity();

            goodsEntity.setGoodsName(dto.getGoodsName());
            goodsEntity.setPrice(dto.getPrice());
            goodsEntity.setSellerId(dto.getSellerId());
            goodsEntity.setDate(ZonedDateTime.now());

            goodsRepository.save(goodsEntity);
        } catch(Exception e) {
            ResponseEntity.internalServerError().build();
        }
        ResponseEntity.ok().build();
    }
}
