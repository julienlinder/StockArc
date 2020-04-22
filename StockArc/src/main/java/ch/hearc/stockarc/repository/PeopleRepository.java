package ch.hearc.stockarc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.stockarc.model.Person;

public interface PeopleRepository extends JpaRepository<Person, Long> {
    
}
