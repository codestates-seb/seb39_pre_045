package pre045.board_service.member.token.refreshtoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pre045.board_service.member.token.refreshtoken.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByTokenKey(String tokenKey);
}
