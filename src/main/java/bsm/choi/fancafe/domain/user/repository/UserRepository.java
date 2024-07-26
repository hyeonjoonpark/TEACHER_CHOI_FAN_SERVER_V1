package bsm.choi.fancafe.domain.user.repository;

import bsm.choi.fancafe.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String username);

    Optional<User> findByUuid(UUID uuid);

    @Query("SELECT u.uuid " +
            "FROM User u " +
            "WHERE u.email = :userEmail")
    String findUserIdByEmail(String userEmail);
}
