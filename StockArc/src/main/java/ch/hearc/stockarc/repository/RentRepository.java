package ch.hearc.stockarc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ch.hearc.stockarc.model.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findAllByCreatedAt(Date createdAt);

    List<Rent> findAllByCreatedAtBetween(Date createdAtStart, Date createdAtEnd, Sort sort);

    @Query("select a from Rent a where a.createdAt <= :createdAt and a.isOver = 0 order by createdAt desc ")
    List<Rent> findAllWithCreatedAtBefore(@Param("createdAt") Date createdAt);
}
