package com.diego.demo.patterns;

import com.diego.demo.indicators.MaxIndicator;
import com.diego.demo.indicators.MinIndicator;
import com.diego.demo.patterns.intermediate.AndMultipleRule;
import com.diego.demo.patterns.intermediate.OffsetedRule;
import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.DifferenceIndicator;
import org.ta4j.core.indicators.helpers.HighPriceIndicator;
import org.ta4j.core.indicators.helpers.HighestValueIndicator;
import org.ta4j.core.indicators.helpers.LowPriceIndicator;
import org.ta4j.core.indicators.helpers.LowestValueIndicator;
import org.ta4j.core.indicators.helpers.MultiplierIndicator;
import org.ta4j.core.indicators.helpers.OpenPriceIndicator;
import org.ta4j.core.indicators.helpers.PreviousValueIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.trading.rules.OverIndicatorRule;

public class TweezerBottoms extends BaseRule {

    public TweezerBottoms(BarSeries series) {
        // Shadow over 2/3 range
        super(new AndMultipleRule(
                new OffsetedRule(1, new RedCandle(series)),
                new GreenCandle(series),
                new OverIndicatorRule(
                        lowerShadow(series),
                        new MultiplierIndicator(
                                new DifferenceIndicator(
                                        high(series),
                                        low(series)
                                ),
                                2.0/3.0
                        )
                )
        ));
    }

    private static Indicator<Num> high(BarSeries series) {
        return new HighestValueIndicator(new HighPriceIndicator(series), 2);
    }

    private static Indicator<Num> low(BarSeries series) {
        return new LowestValueIndicator(new LowPriceIndicator(series), 2);
    }

    private static Indicator<Num> open(BarSeries series) {
        return new MaxIndicator(
                new PreviousValueIndicator(new OpenPriceIndicator(series)),
                new ClosePriceIndicator(series)
        );
    }

    private static Indicator<Num> close(BarSeries series) {
        return new MinIndicator(
                new PreviousValueIndicator(new ClosePriceIndicator(series)),
                new OpenPriceIndicator(series)
        );
    }

    private static Indicator<Num> lowerShadow(BarSeries series) {
        return new DifferenceIndicator(
                new MinIndicator(open(series), close(series)),
                low(series)
        );
    }
}
