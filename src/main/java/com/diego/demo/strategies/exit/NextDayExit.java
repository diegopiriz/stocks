package com.diego.demo.strategies.exit;

import org.ta4j.core.BarSeries;

public class NextDayExit extends PeriodExit  {

    public NextDayExit(BarSeries series) {
        super(series, 1);
    }
}
