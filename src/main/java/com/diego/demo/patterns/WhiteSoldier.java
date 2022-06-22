package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.AndMultipleRule;
import com.diego.demo.patterns.intermediate.OffsetedRule;
import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.OpenPriceIndicator;
import org.ta4j.core.indicators.helpers.PreviousValueIndicator;
import org.ta4j.core.trading.rules.OverIndicatorRule;

// Bullish candle that opens above the previous day close and closes above the previous day open
// Previous is bearish
public class WhiteSoldier extends BaseRule {

    public WhiteSoldier(BarSeries series) {
        super(new AndMultipleRule(
                new OffsetedRule(1, new RedCandle(series)),
                new GreenCandle(series),
                new OverIndicatorRule(
                        new OpenPriceIndicator(series),
                        new PreviousValueIndicator(new ClosePriceIndicator(series))
                ),
                new OverIndicatorRule(
                        new ClosePriceIndicator(series),
                        new PreviousValueIndicator(new OpenPriceIndicator(series))
                )
        ));
    }
}
