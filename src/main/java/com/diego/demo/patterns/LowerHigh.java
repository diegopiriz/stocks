package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.helpers.HighPriceIndicator;
import org.ta4j.core.trading.rules.IsFallingRule;

public class LowerHigh extends BaseRule {

    public LowerHigh(BarSeries series) {
        super(new IsFallingRule(new HighPriceIndicator(series), 1));
    }
}
