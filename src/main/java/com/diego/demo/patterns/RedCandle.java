package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.OpenPriceIndicator;
import org.ta4j.core.trading.rules.OverIndicatorRule;

public class RedCandle extends BaseRule {

    public RedCandle(BarSeries series) {
        super(new OverIndicatorRule(
                new OpenPriceIndicator(series),
                new ClosePriceIndicator(series)
        ));
    }
}
