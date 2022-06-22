package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.helpers.LowPriceIndicator;
import org.ta4j.core.trading.rules.IsFallingRule;

public class LowerLow extends BaseRule {

    public LowerLow(BarSeries series) {
        super(new IsFallingRule(new LowPriceIndicator(series), 1));
    }
}
