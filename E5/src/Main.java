import Controller.PricingController;
import Controller.TickerController;
import Entity.Pricing;
import Entity.Ticker;
import Service.PricingService;
import Service.TickerService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Pricing> pricings = new ArrayList<Pricing>();
        List<Ticker> tickers = new ArrayList<Ticker>();
        tickers.add(new Ticker(1, "Stock", "NASDAQ", "Apple Inc."));
        tickers.add(new Ticker(2, "Bond", "NYSE", "Alphabet Inc."));
        tickers.add(new Ticker(3, "Commodity", "NASDAQ", "Microsoft Corporation"));
        tickers.add(new Ticker(4, "TSLA", "NASDAQ", "Tesla Inc."));
        tickers.add(new Ticker(5, "AMZN", "NASDAQ", "Amazon.com Inc."));
        pricings.add(new Pricing(1, tickers.get(0), LocalDate.of(2024, 1, 10), 145.3, 148.9, 147.5));
        pricings.add(new Pricing(2, tickers.get(1), LocalDate.of(2024, 1, 10), 2700.0, 2750.5, 2735.0));
        pricings.add(new Pricing(3, tickers.get(2), LocalDate.of(2024, 1, 10), 300.5, 310.0, 305.7));
        pricings.add(new Pricing(4, tickers.get(3), LocalDate.of(2024, 1, 10), 650.0, 675.3, 670.0));
        pricings.add(new Pricing(5, tickers.get(4), LocalDate.of(2024, 1, 10), 3300.0, 3350.0, 3325.5));

        PricingService pricingService = new PricingService(pricings);
        TickerService tickerService = new TickerService(tickers);
        PricingController pricingController = new PricingController(pricingService);
        TickerController tickerController = new TickerController(tickerService);

        int choise = -1;
        while (choise != 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("-------------------------------------MENU-------------------------------------");
            System.out.println("1. Filtering Ticker By Symbol");
            System.out.println("2. Grouping Ticker By Exchange");
            System.out.println("3. Calculating Average Price");
            System.out.println("4. Partitioning Based On Price");
            System.out.println("5. Finding The Highest Price");
            System.out.println("6. Collecting To A Map:");
            System.out.println("7. Summing Prices By Symbol");
            System.out.println("8. Joining Ticker Symbols");
            System.out.println("9. Group Pricing By Date");
            System.out.println("10. Creating An Immutable List");
            System.out.println("0. Exit");
            System.out.println("------------------------------------------------------------------------------");
            System.out.print("\nEnter your choice: ");
            choise = scanner.nextInt();
            scanner.nextLine();
            switch (choise) {
                case 1:
                    List<Ticker> foundTickers = tickerController.filteringSymbol("Stock");
                    foundTickers.forEach(System.out::println);
                    break;
                case 2:
                    Map<String, List<Ticker>> groupedTicker = tickerController.groupByExchange();
                    groupedTicker.forEach((key, value) -> {
                        System.out.println(key + ": " + value);
                    });
                    break;
                case 3:
                    double avgPrice = pricingController.calculatingAveragePrice();
                    System.out.println("Avg Price : " + avgPrice);
                    break;
                case 4:
                    Map<Boolean, List<Pricing>> partBaseOnPrice = pricingController.partitioningBasedOnPrice();
                    partBaseOnPrice.forEach((key, value) -> {
                        System.out.println(key + ": " + value);
                    });
                    break;
                case 5:
                    Pricing highestPrice = pricingController.getMaxClosedPrice();
                    System.out.println("Highest Price : " + highestPrice);
                    break;
                case 6:
                    Map<String, Ticker> collectedToMap = tickerController.collectTickersBySymbol();
                    collectedToMap.forEach((key, value) -> {
                        System.out.println(key + ": " + value);
                    });
                    break;
                case 7:
                    Map<String, Double> summedPriceBySymbol = pricingController.summingPricesBySymbol();
                    summedPriceBySymbol.forEach((key, value) -> {
                        System.out.println(key + ": " + value);
                    });
                    break;
                case 8:
                    String joinedString = tickerController.joinTickerSymbol();
                    System.out.println("Joined String : " + joinedString);
                    break;
                case 9:
                    Map<String, Map<LocalDate, List<Pricing>>> groupedByDate = pricingController.groupPricingByDate();
                    groupedByDate.forEach((key, value) -> {
                        System.out.println(key + ": " + value);
                    });
                    break;
                case 10:
                    List<Pricing> createdImmutableList = pricingController.creatingImmutableList();
                    createdImmutableList.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
