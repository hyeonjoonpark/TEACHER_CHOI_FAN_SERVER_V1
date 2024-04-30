package bsm.choi.fancafe.domain.goods.presentation;

import bsm.choi.fancafe.domain.goods.presentation.dto.request.GoodsUploadRequest;
import bsm.choi.fancafe.domain.goods.presentation.dto.response.GoodsResponseDto;
import bsm.choi.fancafe.domain.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;
    @GetMapping("/list")
    public List<GoodsResponseDto> goodsList() {
        List<GoodsResponseDto> result = goodsService.getList();
        return result;
    }

    @PostMapping("/upload")
    public void goodsUpload(@RequestBody GoodsUploadRequest dto) {
        goodsService.saveGoods(dto);
    }
}
