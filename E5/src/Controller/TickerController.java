package Controller;

import Entity.Ticker;
import Service.TickerService;

import java.util.List;
import java.util.Map;

public class TickerController {
    private TickerService tickerService;
    public TickerController(TickerService tickerService) {
        this.tickerService = tickerService;
    }
    public List<Ticker> filteringSymbol(String symbol) {
        return tickerService.filteringSymbol(symbol);
    }
    public Map<String, List<Ticker>> groupByExchange(){
        return tickerService.groupByExchange();
    }
    public Map<String, Ticker> collectTickersBySymbol(){
        return tickerService.collectTickersBySymbol();
    }
    public String joinTickerSymbol(){
        return tickerService.joinTickerSymbol();
    }

}
