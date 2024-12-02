package ie.spring.lab1;

import ie.spring.lab1.services.CalculateCost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@TestPropertySource("classpath:application-test.properties")
@SpringJUnitConfig(classes = {AppConfig.class})
@ActiveProfiles("default")
public class Lab1ApplicationTests {

    @Autowired
    private CalculateCost calculateCost;

    @Test
    public void testCalculateCostBeanNotNull() {
        assertNotNull(calculateCost, "CalculateCost bean should not be null");
    }

    @Test
    public void testCalculateCostFunctionality() throws Exception {
        String weddingId = "RS342";

        double exVat = calculateCost.calculateWeddingCostExVat(weddingId);
        double incVat = calculateCost.calculateWeddingCostIncVat(weddingId);

        System.out.println("Excluding VAT: €" + exVat);
        System.out.println("Including VAT: €" + incVat);

        assertNotNull(exVat, "Ex VAT cost should not be null");
        assertNotNull(incVat, "Inc VAT cost should not be null");
    }
    @Test
    public void testVatCalculation() throws Exception {
        String weddingId = "RS342";

        // Get the values for exVAT and incVAT
        double exVat = calculateCost.calculateWeddingCostExVat(weddingId);
        double incVat = calculateCost.calculateWeddingCostIncVat(weddingId);

        // Expected VAT rate (from application-test.properties)
        double vatRate = 0.15;

        // Calculate expected incVAT manually
        double expectedIncVat = exVat * (1 + vatRate);

        // Assert that the calculated VAT-inclusive cost matches the expected value
        assertEquals(expectedIncVat, incVat, 0.01, "VAT calculation should be correct");
    }
}
