package bsm.choi.fancafe.domain.goods.service;

import bsm.choi.fancafe.domain.goods.Goods;
import bsm.choi.fancafe.domain.goods.presentation.dto.request.GoodsUploadRequest;
import bsm.choi.fancafe.domain.goods.presentation.dto.response.GoodsResponseDto;
import bsm.choi.fancafe.domain.goods.repository.GoodsRepository;
import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class GoodsService {
  private final GoodsRepository goodsRepository;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<GoodsResponseDto> getList() {
    try {
      List<Goods> goodsData = goodsRepository.findAll();
      List<GoodsResponseDto> goodsList = goodsData.stream()
        .map(goods -> new GoodsResponseDto(goods))
        .collect(Collectors.toList());

      return goodsList;
    } catch (GlobalException e) {
      throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  @Transactional
  public void saveGoods(GoodsUploadRequest dto) {
    try {
      String goodsName = dto.goodsName();
      int goodsPrice = dto.price();

      Goods existingGoods = goodsRepository.findByGoodsNameAndPrice(goodsName, goodsPrice);

      if (existingGoods != null) {
        // 이미 동일한 상품이 존재하는 경우, 수량을 증가시킵니다.
        existingGoods.setCount(existingGoods.getCount() + 1);
        goodsRepository.save(existingGoods);
      }
      // 동일한 상품이 존재하지 않는 경우, 새로운 상품을 저장합니다.
      Goods goods = dto.toEntity();

      User user = userRepository.findById(dto.sellerId())
        .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

      // TODO : user가 null인 현상 수정

      goods.setSeller(user);
      user.addGoods(goods);

      goodsRepository.save(goods);
    } catch (GlobalException e) {
      System.out.println(e.getMessage());
      throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }
}
