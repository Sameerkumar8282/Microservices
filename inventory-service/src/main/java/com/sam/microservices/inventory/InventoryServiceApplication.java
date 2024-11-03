package com.sam.microservices.inventory;

import com.sam.microservices.inventory.model.Inventory;
import com.sam.microservices.inventory.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//		return args -> {
//			inventoryRepository.save(new Inventory(null, "iphone_15", 100));
//			inventoryRepository.save(new Inventory(null, "galaxy_24", 50));
//			inventoryRepository.save(new Inventory(null, "pixel_8", 50));
//			inventoryRepository.save(new Inventory(null, "oneplus_12", 50));
//		};
//	}
}
