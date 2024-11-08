package Service;

import Entity.Ticker;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TickerService {
    private List<Ticker> tickers;

    public TickerService( List<Ticker> tickers) {
        this.tickers = tickers;
    }
    public List<Ticker> filteringSymbol(String symbol) {
        List<Ticker> foundTickers = tickers.stream()
                .filter(ticker -> ticker.getSymbol().equalsIgnoreCase(symbol))
                .collect(Collectors.toList());
        return foundTickers;
    }
    public Map<String, List<Ticker>> groupByExchange() {
        return tickers.stream()
                .collect(Collectors.groupingBy(Ticker::getExchange));
    }
    public Map<String, Ticker> collectTickersBySymbol() {
        return tickers.stream()
                .collect(Collectors.toMap(Ticker::getSymbol, ticker -> ticker));
    }
    public String joinTickerSymbol(){
        String stringSymbol = tickers.stream()
                .map(Ticker::getSymbol)
                .collect(Collectors.joining(","));
        return stringSymbol;
    }

}
