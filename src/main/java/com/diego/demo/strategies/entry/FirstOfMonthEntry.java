package com.diego.demo.strategies.entry;

import org.ta4j.core.BarSeries;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

public class FirstOfMonthEntry extends EntryRule {

    public FirstOfMonthEntry(BarSeries series) {
        super(series);
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        ZonedDateTime bt = series.getBar(index).getBeginTime();
        return bt.get(ChronoField.DAY_OF_MONTH) == 1;
    }

    @Override
    public Num getEntryPrice(int index) {
        return series.getBar(index).getOpenPrice();
    }

    @Override
    public String toString() {
        return "FirstOfMonthEntry";
    }
}
