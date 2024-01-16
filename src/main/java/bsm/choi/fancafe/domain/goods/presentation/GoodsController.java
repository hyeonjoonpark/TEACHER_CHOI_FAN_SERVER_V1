package bsm.choi.fancafe.domain.goods.presentation;

import bsm.choi.fancafe.domain.goods.entity.GoodsEntity;
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
    public List<GoodsEntity> goodsList() {
        List<GoodsEntity> result = goodsService.getList();
        return result;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> goodsUpload(@RequestBody GoodsUploadRequestDto dto) {
        goodsService.saveGoods(dto);
        return ResponseEntity.ok().build();
    }
}
