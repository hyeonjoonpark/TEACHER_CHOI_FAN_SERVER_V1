package bsm.choi.fancafe.domain.auth.repository;

import bsm.choi.fancafe.domain.auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

}
