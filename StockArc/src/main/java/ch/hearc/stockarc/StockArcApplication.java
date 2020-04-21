package ch.hearc.stockarc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("ch.hearc.stockarc.repository")
@EntityScan("ch.hearc.stockarc.model")
@SpringBootApplication
public class StockArcApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockArcApplication.class, args);
	}

}
