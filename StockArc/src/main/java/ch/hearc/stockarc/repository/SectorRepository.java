package ch.hearc.stockarc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.stockarc.model.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

}