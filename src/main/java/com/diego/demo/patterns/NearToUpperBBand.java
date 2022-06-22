package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsMiddleIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsUpperIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.DifferenceIndicator;
import org.ta4j.core.indicators.helpers.HighPriceIndicator;
import org.ta4j.core.indicators.helpers.MultiplierIndicator;
import org.ta4j.core.indicators.helpers.SumIndicator;
import org.ta4j.core.indicators.statistics.StandardDeviationIndicator;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

// TODO: Check this!
public class NearToUpperBBand extends BaseRule {

    private static final int DEFAULT_PERIOD = 20;

    public NearToUpperBBand(BarSeries series) {
        super(rule(series));
    }

    private static Rule rule(BarSeries series) {
        return new OverIndicatorRule(
                new HighPriceIndicator(series),
                new SumIndicator(
                        middleBBand(series),
                        new MultiplierIndicator(
                                new DifferenceIndicator(
                                        upperBBand(series),
                                        middleBBand(series)
                                ),
                                0.8
                        )
                )
        );
    }

    private static BollingerBandsMiddleIndicator middleBBand(BarSeries series) {
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        SMAIndicator sma = new SMAIndicator(closePrice, DEFAULT_PERIOD);
        return new BollingerBandsMiddleIndicator(sma);
    }

    private static BollingerBandsUpperIndicator upperBBand(BarSeries series) {
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        StandardDeviationIndicator std = new StandardDeviationIndicator(closePrice, DEFAULT_PERIOD);
        BollingerBandsMiddleIndicator middleBBand = middleBBand(series);
        return new BollingerBandsUpperIndicator(middleBBand, std);
    }
}
