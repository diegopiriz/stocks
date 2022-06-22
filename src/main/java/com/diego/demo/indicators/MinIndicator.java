package com.diego.demo.indicators;

import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.num.Num;

public class MinIndicator extends CachedIndicator<Num> {

    private final Indicator<Num> first;
    private final Indicator<Num> second;

    public MinIndicator(Indicator<Num> first, Indicator<Num> second) {
        super(first.getBarSeries());
        this.first = first;
        this.second = second;
    }

    @Override
    protected Num calculate(int index) {
        Num firstValue = first.getValue(index);
        Num secondValue = second.getValue(index);
        return firstValue.isLessThanOrEqual(secondValue) ? firstValue : secondValue;
    }
}
