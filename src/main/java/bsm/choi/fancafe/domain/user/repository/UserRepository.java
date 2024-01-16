package bsm.choi.fancafe.domain.user.repository;

import bsm.choi.fancafe.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

    boolean existsByIdAndEmail(String id, String email);

    @Query("select u.id, u.email, u.isAdmin from UserEntity u where u.id=:id")
    Optional<Object[]> findUserById(@Param("id") String id);

}
