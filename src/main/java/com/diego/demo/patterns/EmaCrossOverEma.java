package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.CrossOver;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

public class EmaCrossOverEma extends CrossOver {

    public EmaCrossOverEma(BarSeries series, int firstBarCount, int secondBarCount) {
        super(ema(series, firstBarCount), ema(series, secondBarCount));
    }

    private static EMAIndicator ema(BarSeries series, int barCount) {
        return new EMAIndicator(new ClosePriceIndicator(series), barCount);
    }
}
