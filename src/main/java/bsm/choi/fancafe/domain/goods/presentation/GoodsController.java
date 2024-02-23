package bsm.choi.fancafe.domain.goods.presentation;

import bsm.choi.fancafe.domain.goods.Goods;
import bsm.choi.fancafe.domain.goods.presentation.dto.request.GoodsUploadRequestDto;
import bsm.choi.fancafe.domain.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;
    @GetMapping("/list")
    public List<Goods> goodsList() {
        List<Goods> result = goodsService.getList();
        return result;
    }

    @PostMapping("/upload")
    public void goodsUpload(@RequestBody GoodsUploadRequestDto dto) {
        goodsService.saveGoods(dto);
    }
}
