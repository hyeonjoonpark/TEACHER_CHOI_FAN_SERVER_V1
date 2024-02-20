package bsm.choi.fancafe.domain.goods.repository;

import bsm.choi.fancafe.domain.goods.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {

    Goods findByGoodsNameAndPrice(String goodsName, int price);
}
