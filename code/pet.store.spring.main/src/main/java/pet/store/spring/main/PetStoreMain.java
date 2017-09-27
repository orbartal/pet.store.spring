package pet.store.spring.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"pet"})
@EntityScan(basePackages = {"pet"})
@ComponentScan(basePackages = {"pet"})
public class PetStoreMain {
    public static void main(String[] args) {
    	System.out.println("PetStoreMain");
        SpringApplication.run(PetStoreMain.class, args);
    }
}