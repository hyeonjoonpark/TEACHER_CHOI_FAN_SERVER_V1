package bsm.choi.fancafe.domain.goods.repository;

import bsm.choi.fancafe.domain.goods.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {
    GoodsEntity findByGoodsName(String goodsName);
}
