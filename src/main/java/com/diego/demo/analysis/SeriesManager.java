package com.diego.demo.analysis;

import com.diego.demo.strategies.PricedStrategy;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseTradingRecord;
import org.ta4j.core.Order;
import org.ta4j.core.Trade;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.cost.ZeroCostModel;
import org.ta4j.core.indicators.StochasticRSIIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

import java.text.DecimalFormat;

public class SeriesManager {

    private final BarSeries series;

    public SeriesManager(BarSeries series) {
        this.series = series;
    }

    /**
     * Runs the provided strategy over the managed series (from startIndex to
     * finishIndex).
     *
     * @param strategy    the trading strategy
     * @return the trading record coming from the run
     */
    public TradingRecord run(PricedStrategy strategy) {
        int runBeginIndex = series.getBeginIndex() + 1;
        int runEndIndex = series.getEndIndex();

        TradingRecord tradingRecord = new BaseTradingRecord(Order.OrderType.BUY, new ZeroCostModel(), new ZeroCostModel());
        for (int i = runBeginIndex; i <= runEndIndex; i++) {
            Trade trade = tradingRecord.getCurrentTrade();
            if (trade.isNew() && strategy.shouldEnter(i, tradingRecord)) {
                Num entryPrice = strategy.getEntryPrice(i);
                //if(canEnter(i-1, entryPrice)) {
                System.out.println(series.getName() + ": " + series.getBar(i).getSimpleDateName().substring(0, 10) + " BUY  @" + f(entryPrice));
                tradingRecord.enter(i, entryPrice, PrecisionNum.valueOf(1));
                //}
            } else if (trade.isOpened() && strategy.shouldExit(i, tradingRecord)) {
                Num entryPrice = tradingRecord.getCurrentTrade().getEntry().getValue();
                Num exitPrice = strategy.getExitPrice(i, tradingRecord);
                System.out.println(series.getName() + ": " + series.getBar(i).getSimpleDateName().substring(0, 10) + " SELL @" + f(exitPrice) + "   (" + f(exitPrice.minus(entryPrice).dividedBy(entryPrice).multipliedBy(PrecisionNum.valueOf(100))) + "%)");
                tradingRecord.exit(i, exitPrice, PrecisionNum.valueOf(1));
            }
            if(i == runEndIndex && trade.isOpened()) {
                // close all positions
                Num low = series.getBar(i).getLowPrice();
                tradingRecord.exit(i, low, PrecisionNum.valueOf(1));
            }
        }
        return tradingRecord;
    }

    private String f(Num num) {
        return new DecimalFormat("##.00").format(num.doubleValue());
    }

    private boolean canEnter(int index, Num entryPrice) {
        Bar bar = series.getBar(index);
        Num low = bar.getLowPrice();
        Num high = bar.getHighPrice();
        return low.isLessThanOrEqual(entryPrice) && entryPrice.isLessThanOrEqual(high);
    }
}
