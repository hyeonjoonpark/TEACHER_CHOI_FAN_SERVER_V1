package bsm.choi.fancafe.domain.goods.presentation;

import bsm.choi.fancafe.domain.goods.presentation.dto.request.GoodsUploadRequest;
import bsm.choi.fancafe.domain.goods.presentation.dto.response.GoodsResponse;
import bsm.choi.fancafe.domain.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goods", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;
    @GetMapping("/list")
    public List<GoodsResponse> goodsList() {
        List<GoodsResponse> result = goodsService.getList();
        return result;
    }

    @PostMapping("/upload")
    public void goodsUpload(@RequestBody GoodsUploadRequest dto) {
        goodsService.saveGoods(dto);
    }
}
