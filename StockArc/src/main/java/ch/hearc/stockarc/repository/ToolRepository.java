package ch.hearc.stockarc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import ch.hearc.stockarc.model.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    List<Tool> findByNameIsContaining(String name);

    List<Tool> findByNameIsContaining(String name, Sort sort);
}
