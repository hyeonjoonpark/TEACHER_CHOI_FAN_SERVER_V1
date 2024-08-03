package bsm.choi.fancafe.domain.auth.repository;

import bsm.choi.fancafe.domain.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

}
