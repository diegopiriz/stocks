package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.AndMultipleRule;
import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.OverIndicatorRule;

public class EMAUptrend extends BaseRule {

    public EMAUptrend(BarSeries series) {
        super(new AndMultipleRule(
                emaOverEma(series, 50, 100),
                emaOverEma(series, 100, 150),
                emaOverEma(series, 150, 200)
        ));
    }

    private static Rule emaOverEma(BarSeries series, int firstBarCount, int secondBarCount) {
        return new OverIndicatorRule(
                new EMAIndicator(new ClosePriceIndicator(series), firstBarCount),
                new EMAIndicator(new ClosePriceIndicator(series), secondBarCount)
        );
    }
}
