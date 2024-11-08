package Service;

import Entity.Pricing;
import Entity.Ticker;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PricingService {
    private List<Pricing> pricings;

    public PricingService(List<Pricing> pricings) {
        this.pricings = pricings;
    }

    public double calculatingAveragePrice() {
        return pricings.stream()
                .collect(Collectors.averagingDouble(Pricing::getClosePrice));
    }
    public Map<Boolean, List<Pricing>> partitioningBasedOnPrice() {
        return pricings.stream()
                .collect(Collectors.partitioningBy(pricing -> pricing.getClosePrice() > 100));
    }
    public Pricing getMaxClosedPrice() {
        return pricings.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Pricing::getClosePrice)))
                .orElse(null);
    }
    public Map<String, Double> summingPricesBySymbol() {
        return pricings.stream()
                .collect(Collectors.groupingBy(
                        pricing -> pricing.getTicker().getSymbol(),
                        Collectors.summingDouble(Pricing::getClosePrice)
                ));
    }
    public Map<String,Map<LocalDate,List<Pricing>>> groupPricing(){
        Map<String, Map<LocalDate, List<Pricing>>> groupedPricings = pricings.stream()
                .collect(Collectors.groupingBy(
                        pricing -> pricing.getTicker().getSymbol(),
                        Collectors.groupingBy(Pricing::getDateTime)
                ));
        return groupedPricings;
    }
    public List<Pricing> creatingImmutableList(){
        double avgPrice = calculatingAveragePrice();
        List<Pricing> foundPricings = pricings.stream()
                .filter(pricing -> pricing.getClosePrice() > avgPrice)
                .collect(Collectors.toUnmodifiableList());
        return foundPricings;
    }

}
