package com.diego.demo.strategies.exit;

import org.ta4j.core.BarSeries;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

public class PercentageDeltaExit extends ExitRule  {

    private final Num stopLossPercentage;
    private final Num takeProfitPercentage;

    public PercentageDeltaExit(BarSeries series, double stopLossPercentage, double takeProfitPercentage) {
        super(series);
        this.stopLossPercentage = PrecisionNum.valueOf(stopLossPercentage);
        this.takeProfitPercentage = PrecisionNum.valueOf(takeProfitPercentage);
    }

    @Override
    public Num getStopLossPrice(int index, TradingRecord tradingRecord) {
        return entryPrice(tradingRecord).multipliedBy(stopLossPercentage);
    }

    @Override
    public Num getTakeProfitPrice(int index, TradingRecord tradingRecord) {
        return entryPrice(tradingRecord).multipliedBy(takeProfitPercentage);
    }

    private Num entryPrice(TradingRecord tradingRecord) {
        return tradingRecord.getCurrentTrade().getEntry().getValue();
    }

    @Override
    public String toString() {
        return "PercentageDeltaExit:" + stopLossPercentage + " - " + takeProfitPercentage;
    }
}
