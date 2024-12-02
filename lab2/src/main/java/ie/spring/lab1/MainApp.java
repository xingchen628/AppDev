package ie.spring.lab1;

import ie.spring.lab1.repositories.weddings.WeddingRepository;
import ie.spring.lab1.repositories.weddings.Wedding;
import org.springframework.context.ApplicationContext;
import ie.spring.lab1.services.CalculateCost;
import org.springframework.boot.SpringApplication;

import java.util.Optional;

public class MainApp {
    public static void main(String[] args) {
        // Initialize Spring context
        ApplicationContext context = SpringApplication.run(AppConfig.class, args);
        // Access CalculateCost bean
        CalculateCost calculateCost = context.getBean(CalculateCost.class);

        // Demonstrate functionality with CalculateCost bean
        String weddingId = "RS342";
        try {
            double costExVat = calculateCost.calculateWeddingCostExVat(weddingId);
            double costIncVat = calculateCost.calculateWeddingCostIncVat(weddingId);

            System.out.println("Cost for Wedding ID " + weddingId + ":");
            System.out.println("Excluding VAT: €" + costExVat);
            System.out.println("Including VAT: €" + costIncVat);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        // Display bean names
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        // Access WeddingRepository bean by type
        WeddingRepository weddingRepositoryByType = context.getBean(WeddingRepository.class);
        System.out.println("Accessed by type: " + weddingRepositoryByType);

        // Access WeddingRepository bean by name
        WeddingRepository weddingRepositoryByName = (WeddingRepository) context.getBean("weddingRepository");
        System.out.println("Accessed by name: " + weddingRepositoryByName);

        // Use findById() to print details of wedding RS342
        Optional<Wedding> wedding = weddingRepositoryByType.findById("RS342");
        wedding.ifPresentOrElse(
            System.out::println,
            () -> System.out.println("Wedding with ID RS342 not found.")
        );
    }
}
