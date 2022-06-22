package com.diego.demo.strategies.exit;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;

public abstract class ExitRule implements Rule {

    protected final BarSeries series;

    public ExitRule(BarSeries series) {
        this.series = series;
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        return isLoss(index, tradingRecord) || isProfit(index, tradingRecord);
    }

    public Num getExitPrice(int index, TradingRecord tradingRecord) {
        if(isLoss(index, tradingRecord)) {
            return getStopLossPrice(index, tradingRecord);
        }
        return getTakeProfitPrice(index, tradingRecord);
    }

    protected boolean isLoss(int index, TradingRecord tradingRecord) {
        Num low = series.getBar(index).getLowPrice();
        return getStopLossPrice(index, tradingRecord).isGreaterThanOrEqual(low);
    }

    protected boolean isProfit(int index, TradingRecord tradingRecord) {
        Num high = series.getBar(index).getLowPrice();
        return getTakeProfitPrice(index, tradingRecord).isLessThan(high);
    }

    public abstract Num getStopLossPrice(int index, TradingRecord tradingRecord);

    public abstract Num getTakeProfitPrice(int index, TradingRecord tradingRecord);

    public String toString() {
        return "Exit rule";
    }
}
