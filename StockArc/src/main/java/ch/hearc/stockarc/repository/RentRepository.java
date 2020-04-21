package ch.hearc.stockarc.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.stockarc.model.Rent;

public interface RentRepository extends CrudRepository<Rent, Long> {
}
