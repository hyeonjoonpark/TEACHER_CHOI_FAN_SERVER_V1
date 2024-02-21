package bsm.choi.fancafe.domain.goods.repository;

import bsm.choi.fancafe.domain.goods.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer> {

    Goods findByGoodsNameAndPrice(String goodsName, int price);
}
