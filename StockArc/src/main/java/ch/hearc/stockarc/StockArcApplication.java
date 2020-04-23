package ch.hearc.stockarc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ch.hearc.stockarc"})
public class StockArcApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockArcApplication.class, args);
	}

}
