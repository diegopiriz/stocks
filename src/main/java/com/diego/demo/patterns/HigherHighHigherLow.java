package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.trading.rules.AndRule;

public class HigherHighHigherLow extends BaseRule {

    public HigherHighHigherLow(BarSeries series) {
        super(new AndRule(
                new HigherHigh(series),
                new HigherLow(series)
        ));
    }
}
