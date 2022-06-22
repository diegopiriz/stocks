package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.CrossOver;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

public class MacdCrossOverEma extends CrossOver {

    public MacdCrossOverEma(BarSeries series, int shortBarCount, int longBarCount, int emaBarCount) {
        super(macd(series, shortBarCount, longBarCount), ema(series, shortBarCount, longBarCount, emaBarCount));
    }

    private static MACDIndicator macd(BarSeries series, int shortBarCount, int longBarCount) {
        return new MACDIndicator(new ClosePriceIndicator(series), shortBarCount, longBarCount);
    }

    private static EMAIndicator ema(BarSeries series, int shortBarCount, int longBarCount, int emaBarCount) {
        return new EMAIndicator(macd(series, shortBarCount, longBarCount), emaBarCount);
    }
}
