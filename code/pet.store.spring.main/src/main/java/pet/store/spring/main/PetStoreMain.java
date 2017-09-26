package pet.store.spring.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"pet"})
//@EntityScan(basePackages = {"pet"})
@ComponentScan(basePackages = {"pet"})
public class PetStoreMain {
    public static void main(String[] args) {
    	System.out.println("PetStoreMain");
        SpringApplication.run(PetStoreMain.class, args);
    }
}