package com.diego.demo.strategies.entry;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.num.Num;

public abstract class EntryRule implements Rule {

    protected final BarSeries series;

    public EntryRule(BarSeries series) {
        this.series = series;
    }

    public Num getEntryPrice(int index) {
        return series.getBar(index).getHighPrice();
    }

    public String toString() {
        return "Entry rule";
    }
}
