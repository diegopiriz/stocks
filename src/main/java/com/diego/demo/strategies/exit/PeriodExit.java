package com.diego.demo.strategies.exit;

import org.ta4j.core.BarSeries;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

public class PeriodExit extends ExitRule  {

    private final int daysToWait;

    public PeriodExit(BarSeries series, int daysToWait) {
        super(series);
        this.daysToWait = daysToWait;
    }

    @Override
    public Num getStopLossPrice(int index, TradingRecord tradingRecord) {
        int entryIndex = tradingRecord.getLastEntry().getIndex();
        if(entryIndex + daysToWait < index) {
            return series.getBar(index).getLowPrice();
        }
        return PrecisionNum.valueOf(-1);
    }

    @Override
    public Num getTakeProfitPrice(int index, TradingRecord tradingRecord) {
        return PrecisionNum.valueOf(100000);
    }
}
