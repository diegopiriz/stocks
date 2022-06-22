package com.diego.demo.series;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistQuotesRequest;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SeriesFetcher {

    private static HashMap<String, List<HistoricalQuote>> cache = new HashMap<>();

    private final String symbol;

    public SeriesFetcher(String symbol) {
        this.symbol = symbol;
    }

    public BarSeries get() throws IOException {
        return get(HistQuotesRequest.DEFAULT_FROM);
    }

    public BarSeries get(Calendar from) throws IOException {
        BarSeries series = new BaseBarSeriesBuilder().withName(symbol).build();
        Stock stock = YahooFinance.get(symbol);
        if(stock == null) {
            throw new IOException("Not found " + symbol);
        }

        List<HistoricalQuote> history = getFromFileOr(symbol, from);
        if(history == null) {
            history = stock.getHistory(from, Interval.DAILY);
            saveToFile(symbol, from, history);
        }

        history.forEach(hq -> {
            Calendar cal = hq.getDate();
            ZonedDateTime zdt = ZonedDateTime.of(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH)+1,
                    cal.get(Calendar.DAY_OF_MONTH),
                    16, 0, 0, 0,
                    stock.getQuote().getTimeZone().toZoneId()
            );
            if(hq.getClose() != null) {
                series.addBar(Duration.ofMinutes(6 * 60 + 30), zdt, hq.getOpen(), hq.getHigh(), hq.getLow(), hq.getClose(), hq.getVolume());
            }
        });
        return series;
    }

    private List<HistoricalQuote> getFromFileOr(String symbol, Calendar from) {
        String filename = fileName(symbol, from);
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<HistoricalQuote> r = mapper.readValue(Paths.get(filename).toFile(), new TypeReference<List<HistoricalQuote>>(){});
            return r;
        } catch (Exception e) {
            System.out.println(symbol + ": Not found in cache");
            return null;
        }
    }

    private void saveToFile(String symbol, Calendar from, List<HistoricalQuote> history) {
        String filename = fileName(symbol, from);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(filename).toFile(), history);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String fileName(String symbol, Calendar from) {
        int y = from.get(Calendar.YEAR);
        int m = from.get(Calendar.MONTH) + 1;
        int d = from.get(Calendar.DAY_OF_MONTH);
        String date = y + "-" + m + "-" + d;
        return "src/main/resources/cache/" + date + "-" + symbol + ".json";
    }
}
