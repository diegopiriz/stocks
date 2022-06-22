package com.diego.demo.strategies.exit;

import org.ta4j.core.BarSeries;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

public class TrailingStopLossRule extends ExitRule {

    private final ExitRule baseExitRule;
    private Num R;
    private Num currentStop;
    private int currentEntryIndex;

    public TrailingStopLossRule(BarSeries series, ExitRule baseExitRule) {
        super(series);
        this.baseExitRule = baseExitRule;
        this.currentStop = null;
        this.R = null;
        this.currentEntryIndex = -1;
    }

    @Override
    public Num getStopLossPrice(int index, TradingRecord tradingRecord) {
        if(currentStop == null || currentEntryIndex != tradingRecord.getLastEntry().getIndex()) {
            this.currentStop = baseExitRule.getStopLossPrice(index, tradingRecord);
            this.R = tradingRecord.getLastEntry().getValue().minus(currentStop);
            this.currentEntryIndex = tradingRecord.getLastEntry().getIndex();
        }
        // check condition
        Num low = series.getBar(index).getLowPrice();
        if(low.isLessThanOrEqual(currentStop)) {
            return currentStop;
        }
        Num high = series.getBar(index).getHighPrice();
        Num maybeNewStop = high.minus(R);
        if(maybeNewStop.isGreaterThan(currentStop)) {
            this.currentStop = maybeNewStop;
            //System.out.println(series.getBar(index).getSimpleDateName() + " updating new stop price: " + maybeNewStop);
        }
        return currentStop;
    }

    @Override
    public Num getTakeProfitPrice(int index, TradingRecord tradingRecord) {
        //return PrecisionNum.valueOf(Integer.MAX_VALUE);
        return baseExitRule.getTakeProfitPrice(index, tradingRecord);
    }
}
