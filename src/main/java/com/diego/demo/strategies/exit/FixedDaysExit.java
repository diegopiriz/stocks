package com.diego.demo.strategies.exit;

import org.ta4j.core.BarSeries;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

public class FixedDaysExit extends ExitRule  {

    private final int daysToHold;

    public FixedDaysExit(BarSeries series, int daysToHold) {
        super(series);
        this.daysToHold = daysToHold;
    }

    @Override
    public Num getStopLossPrice(int index, TradingRecord tradingRecord) {
        int entryIndex = tradingRecord.getLastEntry().getIndex();
        if(entryIndex + daysToHold <= index - 1) {
            return series.getBar(index).getLowPrice();
        }
        return PrecisionNum.valueOf(Integer.MIN_VALUE);
    }

    @Override
    public Num getTakeProfitPrice(int index, TradingRecord tradingRecord) {
        int entryIndex = tradingRecord.getLastEntry().getIndex();
        if(entryIndex + daysToHold < index) {
            return series.getBar(index).getLowPrice();
        }
        return PrecisionNum.valueOf(Integer.MAX_VALUE);
    }
}
