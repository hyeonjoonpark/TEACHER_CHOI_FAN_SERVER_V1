package bsm.choi.fancafe.domain.goods.service;

import bsm.choi.fancafe.domain.goods.presentation.dto.request.GoodsUploadRequest;
import bsm.choi.fancafe.domain.goods.presentation.dto.response.GoodsResponseDto;
import bsm.choi.fancafe.domain.goods.repository.GoodsRepository;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class GoodsService {
  private final GoodsRepository goodsRepository;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<GoodsResponseDto> getList() {
    return null;
  }

  @Transactional
  public void saveGoods(GoodsUploadRequest dto) {

  }
}
