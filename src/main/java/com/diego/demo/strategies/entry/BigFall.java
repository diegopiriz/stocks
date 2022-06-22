package com.diego.demo.strategies.entry;

import com.diego.demo.indicators.MaxInPeriodIndicator;
import org.ta4j.core.BarSeries;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

public class BigFall extends EntryRule {

    private final Num multiplier;
    private final MaxInPeriodIndicator indicator;

    public BigFall(BarSeries series, int fallPercentage) {
        super(series);
        this.multiplier = PrecisionNum.valueOf(1.0 - fallPercentage / 100.0);
        this.indicator = new MaxInPeriodIndicator(new ClosePriceIndicator(series), 22);
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        Num weeklyMax = indicator.getValue(index);
        Num close = series.getBar(index).getClosePrice();
        return weeklyMax.multipliedBy(multiplier).isGreaterThanOrEqual(close);
//        if(index < 1) {
//            return false;
//        }
//        Num close = series.getBar(index).getClosePrice();
//        Num previousClose = series.getBar(index - 1).getClosePrice();
//        return previousClose.multipliedBy(multiplier).isGreaterThanOrEqual(close);
    }

    @Override
    public Num getEntryPrice(int index) {
        return series.getBar(index).getOpenPrice();
    }

    @Override
    public String toString() {
        return "BigFall: " + multiplier;
    }
}
