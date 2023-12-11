package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.entity.GoodsEntity;
import bsm.choi.fancafe.domain.user.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsListService {
    private final GoodsRepository goodsRepository;
    public List<GoodsEntity> goodsList() {
        List<GoodsEntity> result = goodsRepository.findAll();
        return result;
    }
}
