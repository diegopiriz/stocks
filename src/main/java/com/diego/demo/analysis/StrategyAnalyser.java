package com.diego.demo.analysis;

import com.diego.demo.strategies.PricedStrategy;
import org.ta4j.core.BarSeries;
import org.ta4j.core.TradingRecord;

public class StrategyAnalyser {

    private final BarSeries series;
    private final PricedStrategy strategy;

    public StrategyAnalyser(BarSeries series, PricedStrategy strategy) {
        this.series = series;
        this.strategy = strategy;
    }

    public AnalysisResult analyze() {
        SeriesManager manager = new SeriesManager(series);
        TradingRecord tradingRecord = manager.run(strategy);
        return new AnalysisResult(series.getName(), tradingRecord);
    }
}
