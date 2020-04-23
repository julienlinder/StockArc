package ch.hearc.stockarc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import ch.hearc.stockarc.model.Person;

public interface PeopleRepository extends JpaRepository<Person, Long> {

    List<Person> findByNameIsContaining(String name);

    List<Person> findByNameIsContaining(String name, Sort sort);

}
