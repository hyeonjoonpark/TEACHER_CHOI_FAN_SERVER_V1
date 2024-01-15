package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.entity.GoodsEntity;
import bsm.choi.fancafe.domain.user.presentation.dto.request.GoodsUploadRequestDto;
import bsm.choi.fancafe.domain.user.repository.GoodsRepository;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    public List<GoodsEntity> goodsList() {
        List<GoodsEntity> result = goodsRepository.findAll();
        return result;
    }
    public void save(GoodsUploadRequestDto dto) {
        try {
            dto.toEntity();
            goodsRepository.save(dto);
        } catch (GlobalException e) {
            throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
