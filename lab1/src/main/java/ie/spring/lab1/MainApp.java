package ie.spring.lab1;

import ie.spring.lab1.repositories.weddings.MockWeddingRepositoryImpl;
import ie.spring.lab1.services.CalculateCost;
import ie.spring.lab1.services.CalculateCostImplementation;

public class MainApp {
    public static void main(String[] args) {
        // Create a new MockWeddingRepositoryImpl object
        MockWeddingRepositoryImpl weddingRepository = new MockWeddingRepositoryImpl();

        // Create a CalculateCostImplementation object and wire the WeddingRepository
        CalculateCost calculateCost = new CalculateCostImplementation(weddingRepository);

        // Demonstrate the functionality
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
    }
}
