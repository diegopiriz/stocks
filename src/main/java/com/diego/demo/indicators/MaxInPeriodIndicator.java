package com.diego.demo.indicators;

import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.num.Num;

public class MaxInPeriodIndicator extends CachedIndicator<Num> {

    private final Indicator<Num> indicator;
    private final int days;

    public MaxInPeriodIndicator(Indicator<Num> indicator, int days) {
        super(indicator.getBarSeries());
        this.indicator = indicator;
        this.days = days;
    }

    @Override
    protected Num calculate(int index) {
        Num max = null;
        for (int i = 0; i < days; i++) {
            Num value = indicator.getValue(index - i);
            if(max == null || max.isLessThanOrEqual(value)) {
                max = value;
            }
        }
        return max;
    }
}
