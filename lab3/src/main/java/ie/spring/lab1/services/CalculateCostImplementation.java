package ie.spring.lab1.services;

import ie.spring.lab1.repositories.weddings.Wedding;
import ie.spring.lab1.repositories.weddings.WeddingRepository;
import ie.spring.lab1.services.utilities.TidyMoney;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalculateCostImplementation implements CalculateCost {

    private static final Logger log = LoggerFactory.getLogger(CalculateCostImplementation.class);

    private final WeddingRepository weddingRepository;

    @Value("${vat.rate}")
    private double tax_rate;

    @Autowired
    public CalculateCostImplementation(WeddingRepository weddingRepository) {
        this.weddingRepository = weddingRepository;
    }

    @PostConstruct
    public void init() {
        log.info("CalculateCostImplementation bean has been created.");
    }

    @PreDestroy
    public void destroy() {
        log.info("CalculateCostImplementation bean is about to be destroyed.");
    }

    @Override
    public double calculateWeddingCostExVat(String weddingId) throws Exception {
        return weddingRepository.findById(weddingId)
            .map(wedding -> {
                try {
                    int numberOfGuests = weddingRepository.getNumberOfGuests(weddingId);
                    return wedding.getCostPerGuest() * numberOfGuests;
                } catch (Exception e) {
                    throw new RuntimeException("Error calculating cost for wedding: " + e.getMessage(), e);
                }
            })
            .orElseThrow(() -> new Exception("Wedding with ID " + weddingId + " not found."));
    }

    @Override
    public double calculateWeddingCostIncVat(String weddingId) throws Exception {
        double costExVat = calculateWeddingCostExVat(weddingId);
        double costIncVat = costExVat * (1 + tax_rate);
        return TidyMoney.twoDigits(costIncVat);
    }
}
