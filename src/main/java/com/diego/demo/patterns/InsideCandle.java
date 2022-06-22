package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.trading.rules.AndRule;

public class InsideCandle extends BaseRule {

    public InsideCandle(BarSeries series) {
        super(new AndRule(
                new LowerHigh(series),
                new HigherLow(series)
        ));
    }
}
