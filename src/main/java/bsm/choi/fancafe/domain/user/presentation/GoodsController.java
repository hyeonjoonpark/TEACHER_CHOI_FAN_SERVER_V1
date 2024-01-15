package bsm.choi.fancafe.domain.user.presentation;

import bsm.choi.fancafe.domain.user.entity.GoodsEntity;
import bsm.choi.fancafe.domain.user.presentation.dto.request.GoodsUploadRequestDto;
import bsm.choi.fancafe.domain.user.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;
    @GetMapping("/goods/list")
    public List<GoodsEntity> goodsList() {
        List<GoodsEntity> result = goodsService.goodsList();
        return result;
    }

    @PostMapping("/goods/upload")
    public ResponseEntity<String> goodsUpload(@RequestBody GoodsUploadRequestDto dto) {
        goodsService.save(dto);
        return ResponseEntity.ok().build();
    }
}
