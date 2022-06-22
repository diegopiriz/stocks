package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.helpers.HighPriceIndicator;
import org.ta4j.core.trading.rules.IsRisingRule;

public class HigherHigh extends BaseRule {

    public HigherHigh(BarSeries series) {
        super(new IsRisingRule(new HighPriceIndicator(series), 1));
    }
}
