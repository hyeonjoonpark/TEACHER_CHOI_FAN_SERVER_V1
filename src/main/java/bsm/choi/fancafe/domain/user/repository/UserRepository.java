package bsm.choi.fancafe.domain.user.repository;

import bsm.choi.fancafe.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    @Query("select u.id, u.email " +
            "from User u " +
            "where u.id=:id")
    Optional<Object[]> findUserById(@Param("id") String id);

}
