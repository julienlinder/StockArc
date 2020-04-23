package ch.hearc.stockarc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.stockarc.model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
    
}
