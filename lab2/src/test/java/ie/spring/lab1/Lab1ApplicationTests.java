package ie.spring.lab1;

import ie.spring.lab1.services.CalculateCost;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Lab1ApplicationTests {

    private final CalculateCost calculateCost;

    public Lab1ApplicationTests() {
        // Create Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Access CalculateCost bean
        calculateCost = context.getBean(CalculateCost.class);
        context.close();
    }

    @Test
    public void testCalculateCostBeanNotNull() {
        assertNotNull(calculateCost, "CalculateCost bean should not be null");
    }
    @Test
    public void testCalculateCostFunctionality() throws Exception {
        String weddingId = "RS342";

        // Verify the CalculateCost bean is functional
        double exVat = calculateCost.calculateWeddingCostExVat(weddingId);
        double incVat = calculateCost.calculateWeddingCostIncVat(weddingId);

        System.out.println("Excluding VAT: €" + exVat);
        System.out.println("Including VAT: €" + incVat);

        assertNotNull(exVat, "Ex VAT cost should not be null");
        assertNotNull(incVat, "Inc VAT cost should not be null");
    }

}
