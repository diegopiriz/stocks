package com.diego.demo.patterns;


import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.helpers.LowPriceIndicator;
import org.ta4j.core.trading.rules.IsRisingRule;

public class HigherLow extends BaseRule {

    public HigherLow(BarSeries series) {
        super(new IsRisingRule(new LowPriceIndicator(series), 1));
    }
}
