package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.OrMultipleRule;
import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.AbsoluteIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.DifferenceIndicator;
import org.ta4j.core.indicators.helpers.LowPriceIndicator;
import org.ta4j.core.indicators.helpers.MultiplierIndicator;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

public class SupportedOnEMA extends BaseRule {

    private final static double SUPPORT_THRESHOLD = 0.01;

    public SupportedOnEMA(BarSeries series) {
        super(new OrMultipleRule(
                supportedOnEMA(series, 50),
                supportedOnEMA(series, 100),
                supportedOnEMA(series, 150),
                supportedOnEMA(series, 200)
        ));
    }

    private static Rule supportedOnEMA(BarSeries series, int barCount) {
        return new UnderIndicatorRule(
                new AbsoluteIndicator(
                        new DifferenceIndicator(
                                new LowPriceIndicator(series),
                                new EMAIndicator(new ClosePriceIndicator(series), barCount)
                        )
                ),
                new MultiplierIndicator(
                        new EMAIndicator(new ClosePriceIndicator(series), barCount),
                        SUPPORT_THRESHOLD
                )
        );
    }
}
