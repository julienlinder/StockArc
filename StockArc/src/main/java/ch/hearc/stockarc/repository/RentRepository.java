package ch.hearc.stockarc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.hearc.stockarc.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findAllByCreatedAt(Date created_at);

    List<Rent> findAllByCreatedAtBetween(Date created_at_start, Date created_at_end);

    @Query("select a from Rent a where a.createdAt <= :createdAt and a.isOver = false")
    List<Rent> findAllWithCreatedAteBefore(@Param("createdAt") Date createdAt);
}
