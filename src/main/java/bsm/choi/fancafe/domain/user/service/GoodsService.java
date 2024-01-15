package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.entity.GoodsEntity;
import bsm.choi.fancafe.domain.user.presentation.dto.request.GoodsUploadRequestDto;
import bsm.choi.fancafe.domain.user.repository.GoodsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
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

    public void upload(GoodsUploadRequestDto dto) {

    }
}
