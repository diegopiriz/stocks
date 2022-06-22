package com.diego.demo;

//https://query1.finance.yahoo.com/v7/finance/options/NIO
//https://www.optionsprofitcalculator.com/ajax/getOptions?stock=NIO&reqId=1

import com.diego.demo.analysis.AnalysisResult;
import com.diego.demo.analysis.Recommendation;
import com.diego.demo.analysis.StrategyAnalyser;
import com.diego.demo.series.SeriesFetcher;
import com.diego.demo.strategies.PricedStrategy;
import com.diego.demo.strategies.entry.*;
import com.diego.demo.strategies.exit.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseTradingRecord;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;
import yahoofinance.YahooFinance;
import yahoofinance.util.RedirectableRequest;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);


        final double OK = 33.0;
        final double WRONG = 67.0;
        final double PROB_SUCCESS = 0.652;

        final double INITIAL_BALANCE = 1000;
        final double NUM_EXPERIMENTS = 1200;


        double balance = INITIAL_BALANCE;
        for (int i = 0; i < NUM_EXPERIMENTS; i++) {
            if(Math.random() < PROB_SUCCESS) {
                balance += OK;
            } else {
                balance -= WRONG;
            }
            if(balance < WRONG) {
                System.out.println("OUT OF MONEY at " + i);
                break;
            }
        }
        System.out.println("SUCCESS " + balance);






        System.exit(0);





        //String[] symbols = Stocks.revolut;
        //String[] symbols = Stocks.revolutPopular;
        String[] symbols = {"^GSPC"};
        //String[] symbols = {"AM", "UNH", "YETI", "ALGN"};

        checkStrategy(symbols);
        //findOpportunities(symbols);
        //checkRecommendations(symbols);
        //test(symbols);

        System.exit(0);
    }

    private static List<Recommendation> checkRecommendations(String[] symbols) {
        return Arrays.stream(symbols)
                .parallel()
                .map(DemoApplication::getRecommendation)
                .filter(Objects::nonNull)
                .filter(r -> r.getTotal() > 10)
                .filter(r -> r.getScore() <= 1.5)
                .collect(Collectors.toList())
                .stream()
                .sorted()
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    private static Recommendation getRecommendation(String symbol) {
        ////https://query2.finance.yahoo.com/v10/finance/quoteSummary/FB?modules=recommendationTrend
        final String url = "https://query2.finance.yahoo.com/v10/finance/quoteSummary/" + symbol + "?modules=recommendationTrend";
        try {
            URL request = new URL(url);
            RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
            redirectableRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
            redirectableRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
            URLConnection connection = redirectableRequest.openConnection();

            InputStreamReader is = new InputStreamReader(connection.getInputStream());
            JsonNode node = new ObjectMapper().readTree(is);
            if (node.has("quoteSummary") && node.get("quoteSummary").has("result")) {
                node = node.get("quoteSummary").get("result").get(0).get("recommendationTrend").get("trend").get(0);
                int strongBuy = node.get("strongBuy").asInt();
                int buy = node.get("buy").asInt();
                int hold = node.get("hold").asInt();
                int sell = node.get("sell").asInt();
                int strongSell = node.get("strongSell").asInt();
                return new Recommendation(symbol, strongBuy, buy, hold, sell, strongSell);
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static void checkStrategy(String[] symbols) {
        //List<String> recommended = checkRecommendations(symbols)
        //        .stream()
        //        .map(Recommendation::getSymbol)
        //        .collect(Collectors.toList());
        //List<String> recommended = new LinkedList<>();
        //System.out.println("Will check ("+recommended.size()+"):" + String.join(", ", recommended));

        List<AnalysisResult> partialResults = Arrays.asList(symbols)
                .stream()
                //.parallelStream()
                //.filter(recommended::contains)
                .map(symbol -> {
                    System.out.println("Checking: " + symbol);
                    try {
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.YEAR, -2);
                        BarSeries series = new SeriesFetcher(symbol).get(cal);
                        PricedStrategy strategy = strategy(series);
                        return new StrategyAnalyser(series, strategy).analyze();
                    } catch (FileNotFoundException e) {
                    } catch (Exception e) {
                        System.out.println("Error with symbol: " + symbol);
                        //e.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        AnalysisResult totalResult = new AnalysisResult();
        for (AnalysisResult ar : partialResults) {
            totalResult.add(ar);
        }
        totalResult.print();
    }

    private static void findOpportunities(String[] symbols) {
        Arrays.asList(symbols).parallelStream().forEach(symbol -> {
            try {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, -1);
                BarSeries series = new SeriesFetcher(symbol).get(cal);
                PricedStrategy strategy = strategy(series);
                int index = series.getEndIndex();
                if (strategy.shouldEnter(index)) {
                    Num entryPrice = strategy.getEntryPrice(index);
                    BaseTradingRecord tradingRecord = new BaseTradingRecord();
                    tradingRecord.operate(index, entryPrice, PrecisionNum.valueOf(1));
                    Num stopLoss = strategy.getExitRule().getStopLossPrice(index, tradingRecord);
                    System.out.println(symbol + " @" + f(entryPrice) + "  -  @" + f(stopLoss));
                }
            } catch (Exception e) {
            }
        });
    }

    private static PricedStrategy strategy(BarSeries series) {
        return new PricedStrategy(
                //new MyEntry(series),
                new BigFall(series, 5),
                //new FirstOfMonthEntry(series),
                //new FixedDaysExit(series, 222)
                //new ImpulsePullbackEntry(series),
                //new WhiteSoldierEntry(series),
                //new TrailingStopLossRule(series, new RExit(series, 0.95, 1.15))
                //new TrailingStopLossRule(series, new RExit(series, 1.0, 2.5))
                //new PercentageDeltaExit(series, 0.001, 1.20)
                //new TrailingStopLossRule(series, new PercentageDeltaExit(series, 0.9, 1.50))
                new FixedDaysExit(series, 60)
        );
    }

    // bests revolutPopular:
    // new BigFall(series, 15), new FixedDaysExit(series, 60) -> 1215%
    // new BigFall(series, 15), new FixedDaysExit(series, 90) -> 307%

    private static String f(Num num) {
        return new DecimalFormat("##.00").format(num.doubleValue());
    }
}



// check, buy stocks with recommendations above 1.XX on Aug 1st and check returns after 7, 14 and 30 days



//private static final int NUM_EXPERIMENTS = 100000;
//    private static final double INITIAL_BALANCE = 5000;
//    private static final double INITIAL_STAKE = 1;
//    private static final double LOOSE_MULTIPLIER = 2;
//    private static final double WIN_MULTIPLIER = 3;
//    private static final double WIN_PROB = 1.0 * 12 / 37;
//
//    public static void main(String[] args) {
//
//        double winCount = 0;
//        double aggBalance = 0;
//
//        for (int i = 0; i < NUM_EXPERIMENTS; i++) {
//            double balance = INITIAL_BALANCE;
//            double stake = INITIAL_STAKE;
//            double accumLosses = 0;
//            int roundsPlayed = 0;
//
//            System.out.println("Experiment #" + i);
//            while (stake < balance) { // do we have enough money to bet?
//                System.out.print("\tBetting $" + stake + " -> ");
//                balance = balance - stake; // you need to pay to bet
//                accumLosses = accumLosses + stake;
//                roundsPlayed = roundsPlayed + 1;
//                boolean isWin = Math.random() < WIN_PROB;
//                if (isWin) {
//                    // Congratulations, you won!
//                    System.out.println("You win!");
//                    balance = balance + stake * WIN_MULTIPLIER;
//                    winCount = winCount + 1;
//                    break; // Stop the experiment
//                } else {
//                    // You loose, better luck next time
//                    System.out.println("You loose (accum losses: " + accumLosses + ")");
//                    stake = stake * LOOSE_MULTIPLIER;
//                    //stake = INITIAL_STAKE + accumLosses / (WIN_MULTIPLIER - 1);
//                    //stake = accumLosses / (WIN_MULTIPLIER - 1);
//                }
//            }
//            System.out.println("\tBalance: $" + (balance - INITIAL_BALANCE) + " Tries: " + roundsPlayed + "\n\n");
//            aggBalance += balance - INITIAL_BALANCE;
//        }
//
//        System.out.println("RESULTS:");
//        System.out.println("\tEXPERIMENTS: " + NUM_EXPERIMENTS);
//        double winRate = (100.0 * winCount / NUM_EXPERIMENTS);
//        System.out.println("\tMAX RISK: $" + INITIAL_BALANCE);
//        System.out.println("\tYOU WON: " + winRate + "%");
//        System.out.println("\tYOU LOOSE: " + (100 - winRate)  + "%");
//        //System.out.println("\tAgg. Balance: $" + aggBalance);
//        System.out.println("\tAvg. Balance: +$" + aggBalance / NUM_EXPERIMENTS);
//        System.out.println("\tBalance/Risk: " + 100.0 * (aggBalance / NUM_EXPERIMENTS) / INITIAL_BALANCE + "%");
//    }
