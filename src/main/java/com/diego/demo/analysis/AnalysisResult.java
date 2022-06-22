package com.diego.demo.analysis;


import org.ta4j.core.Trade;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalysisResult {
    public int closedTrades;
    public int profitableTrades;
    public int losingTrades;
    public double profitPercentage;
    public double lossPercentage;
    public Map<String, TradingRecord> tradingRecords;

    public AnalysisResult(String symbol, TradingRecord tradingRecord) {
        this.closedTrades = tradingRecord.getTrades().size();
        this.profitableTrades = (int) tradingRecord.getTrades().stream().filter(t -> t.isClosed() && t.getProfit().isPositive()).count();
        this.losingTrades = (int) tradingRecord.getTrades().stream().filter(t -> t.isClosed() && t.getProfit().isNegativeOrZero()).count();
        List<Num> balances = tradingRecord.getTrades().stream()
                .map(trade -> trade.getExit().getValue()
                        .minus(trade.getEntry().getValue())
                        .dividedBy(trade.getEntry().getValue())
                        .multipliedBy(PrecisionNum.valueOf(100))
                )
                .collect(Collectors.toList());
        this.profitPercentage = balances.stream().filter(Num::isPositive).reduce(PrecisionNum.valueOf(0.0), Num::plus).doubleValue();
        this.lossPercentage = balances.stream().filter(Num::isNegativeOrZero).reduce(PrecisionNum.valueOf(0.0), Num::plus).doubleValue();
        this.tradingRecords = Collections.singletonMap(symbol, tradingRecord);
    }

    public AnalysisResult() {
        this.closedTrades = 0;
        this.profitableTrades = 0;
        this.losingTrades = 0;
        this.profitPercentage = 0.0;
        this.lossPercentage = 0.0;
        this.tradingRecords = new HashMap<>();
    }

    public void print() {
        if(closedTrades > 0) {
            System.out.println("Closed Trades:      " + closedTrades);
            System.out.println("Profitable trades:  " + profitableTrades);
            System.out.println("Loosing trades:     " + losingTrades);
            System.out.println("Strategy Perf.:     " + format(100.0 * profitableTrades / closedTrades) + "%");
            System.out.println("Avg Profit:         " + format(profitPercentage / profitableTrades) + "%");
            System.out.println("Avg Loss:           " + format(lossPercentage / losingTrades) + "%");
            double balance = 0;
            if(profitableTrades > 0) {
                balance += profitableTrades * (1 + (profitPercentage / (100 * profitableTrades)));
            }
            if(losingTrades > 0) {
                balance += losingTrades * (1 + (lossPercentage / (100 * losingTrades)));
            }
            balance /= closedTrades;
            System.out.println("Balance:            " + format(100 * balance - 100) + "%");
            int sumDays = 0;
            int sumTrades = 0;
            for(TradingRecord tr: tradingRecords.values()) {
                for(Trade trade: tr.getTrades()) {
                    sumDays += trade.getExit().getIndex() - trade.getEntry().getIndex();
                }
                sumTrades += tr.getTradeCount();
            }
            double avgDays = 1.0 * sumDays / sumTrades;
            System.out.println("Avg. Exit (days):   " + format(avgDays));
            System.out.println("Annual Return:      " + format((Math.pow(balance, 365.0 / avgDays) - 1) * 100) + "%");
        }
    }

    private String format(double num) {
        return new DecimalFormat("#.##").format(num);
    }

    public void add(AnalysisResult other) {
        this.closedTrades += other.closedTrades;
        this.profitableTrades += other.profitableTrades;
        this.losingTrades += other.losingTrades;
        this.profitPercentage += other.profitPercentage;
        this.lossPercentage += other.lossPercentage;
        this.tradingRecords.putAll(other.tradingRecords);
    }
}
