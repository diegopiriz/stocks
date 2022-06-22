package com.diego.demo.strategies.exit;

import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

public class RExit extends ExitRule  {

    private final Num stopLossMultiplier;
    private final Num takeProfitMultiplier;

    public RExit(BarSeries series, double stopLossMultiplier, double takeProfitMultiplier) {
        super(series);
        this.stopLossMultiplier = PrecisionNum.valueOf(stopLossMultiplier);
        this.takeProfitMultiplier = PrecisionNum.valueOf(takeProfitMultiplier);
    }

    @Override
    public Num getStopLossPrice(int index, TradingRecord tradingRecord) {
        return entryPrice(tradingRecord).minus(getR(tradingRecord).multipliedBy(stopLossMultiplier));
    }

    @Override
    public Num getTakeProfitPrice(int index, TradingRecord tradingRecord) {
        return entryPrice(tradingRecord).plus(getR(tradingRecord).multipliedBy(takeProfitMultiplier));
    }

    private Num getR(TradingRecord tradingRecord) {
        Num entryPrice = entryPrice(tradingRecord);
        int entryIndex = tradingRecord.getCurrentTrade().getEntry().getIndex();
        Bar bar = series.getBar(entryIndex);
        return entryPrice.minus(bar.getLowPrice());
    }

    private Num entryPrice(TradingRecord tradingRecord) {
        return tradingRecord.getCurrentTrade().getEntry().getValue();
    }
}
