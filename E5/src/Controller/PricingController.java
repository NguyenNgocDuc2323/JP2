package Controller;

import Entity.Pricing;
import Service.PricingService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PricingController {
    private PricingService pricingService;
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }
    public double calculatingAveragePrice(){
        return pricingService.calculatingAveragePrice();
    }
    public Map<Boolean, List<Pricing>> partitioningBasedOnPrice(){
        return pricingService.partitioningBasedOnPrice();
    }
    public Pricing getMaxClosedPrice(){
        return pricingService.getMaxClosedPrice();
    }
    public Map<String, Double> summingPricesBySymbol(){
        return pricingService.summingPricesBySymbol();
    }
    public Map<String,Map<LocalDate,List<Pricing>>> groupPricingByDate(){
        return pricingService.groupPricing();
    }
    public List<Pricing> creatingImmutableList(){
        return pricingService.creatingImmutableList();
    }
}
