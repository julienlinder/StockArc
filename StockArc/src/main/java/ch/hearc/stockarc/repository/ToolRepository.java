package ch.hearc.stockarc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.stockarc.model.Tool;

public interface ToolRepository extends JpaRepository<Tool, Long> {

}
