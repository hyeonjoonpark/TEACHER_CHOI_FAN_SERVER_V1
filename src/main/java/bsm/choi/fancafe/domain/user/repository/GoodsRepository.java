package bsm.choi.fancafe.domain.user.repository;

import bsm.choi.fancafe.domain.user.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {

}
