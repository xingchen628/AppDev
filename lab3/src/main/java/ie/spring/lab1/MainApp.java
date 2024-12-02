package ie.spring.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;

import java.util.Locale;

@SpringBootApplication
public class MainApp implements CommandLineRunner {

    @Autowired
    private MessageSource messageSource;

    public static void main(String[] args) {
        // Launch Spring Boot application
        SpringApplication.run(MainApp.class, args);
    }

    @Override
    public void run(String... args) {
        // Access MessageSource and print messages
        System.out.println("Default Language: " + messageSource.getMessage("greeting", null, Locale.ENGLISH));
        System.out.println("French Language: " + messageSource.getMessage("greeting", null, Locale.FRENCH));
    }
}
