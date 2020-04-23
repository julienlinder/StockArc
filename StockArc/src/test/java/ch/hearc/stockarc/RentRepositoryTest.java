package ch.hearc.stockarc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ch.hearc.stockarc.model.Rent;
import ch.hearc.stockarc.repository.RentRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { StockArcApplication.class })
public class RentRepositoryTest {

    @Autowired
    private RentRepository rentRepository;

    @Test
    public void givenRentRepository_whenSaveAndRetreiveRent_thenOK() {
        Rent rent = rentRepository.save(new Rent());
        Optional<Rent> foundRent = rentRepository.findById(rent.getId());

        assertTrue(foundRent.isPresent());
        assertEquals(rent.getId(), foundRent.get().getId());

        rentRepository.delete(foundRent.get());
    }

}