package ch.hearc.stockarc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.stockarc.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);

}
