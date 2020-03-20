package ch.hearc.stockarc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.stockarc.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
