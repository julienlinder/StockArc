package ch.hearc.stockarc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.stockarc.model.Sector;

public interface SectorRepository extends JpaRepository<Sector, Long> {

}