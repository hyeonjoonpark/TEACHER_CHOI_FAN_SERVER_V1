package bsm.choi.fancafe.domain.user.presentation;

import bsm.choi.fancafe.domain.user.entity.GoodsEntity;
import bsm.choi.fancafe.domain.user.service.GoodsListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GoodsListController {
    private final GoodsListService goodsListService;
    @GetMapping("/goods/list")
    public List<GoodsEntity> goodsList() {
        List<GoodsEntity> result = goodsListService.goodsList();
        return result;
    }
}
