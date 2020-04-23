package ch.hearc.stockarc.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ch.hearc.stockarc.model.UserCreationToken;

@Repository
public interface UserCreationTokenRepository extends JpaRepository<UserCreationToken, Long> {
    
    UserCreationToken findByToken(String token);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from UserCreationToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);

}
