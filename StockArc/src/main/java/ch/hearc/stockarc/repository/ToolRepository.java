package ch.hearc.stockarc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.stockarc.model.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
}
