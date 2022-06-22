package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.OpenPriceIndicator;
import org.ta4j.core.trading.rules.OverIndicatorRule;

public class GreenCandle extends BaseRule {

    public GreenCandle(BarSeries series) {
        super(new OverIndicatorRule(
                new ClosePriceIndicator(series),
                new OpenPriceIndicator(series)
        ));
    }
}
