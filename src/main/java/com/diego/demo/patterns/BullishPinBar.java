package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.candles.LowerShadowIndicator;
import org.ta4j.core.indicators.helpers.DifferenceIndicator;
import org.ta4j.core.indicators.helpers.HighPriceIndicator;
import org.ta4j.core.indicators.helpers.LowPriceIndicator;
import org.ta4j.core.indicators.helpers.MultiplierIndicator;
import org.ta4j.core.trading.rules.OverIndicatorRule;

// It's a bullish Pin Bar if Body is small and long low shadow (2/3)
public class BullishPinBar extends BaseRule {

    public BullishPinBar(BarSeries series) {
        // Shadow over 2/3 range
        super(new OverIndicatorRule(
                new LowerShadowIndicator(series),
                new MultiplierIndicator(
                    new DifferenceIndicator(
                        new HighPriceIndicator(series),
                        new LowPriceIndicator(series)
                    ),
                    2.0/3.0
                )
        ));
    }
}
