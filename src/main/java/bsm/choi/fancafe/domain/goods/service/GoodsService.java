package bsm.choi.fancafe.domain.goods.service;

import bsm.choi.fancafe.domain.goods.presentation.dto.request.GoodsUploadRequest;
import bsm.choi.fancafe.domain.goods.presentation.dto.response.GoodsResponse;
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

  @Transactional(
          readOnly = true,
          rollbackFor = Exception.class
  )
  public List<GoodsResponse> getList() {
    return null;
  }

  @Transactional(rollbackFor = Exception.class)
  public void saveGoods(GoodsUploadRequest dto) {

  }
}
