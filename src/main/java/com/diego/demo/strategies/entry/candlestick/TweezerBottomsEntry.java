package com.diego.demo.strategies.entry.candlestick;

import com.diego.demo.patterns.intermediate.ConsecutivePattern;
import com.diego.demo.patterns.EMAUptrend;
import com.diego.demo.patterns.SupportedOnEMA;
import com.diego.demo.patterns.TweezerBottoms;
import com.diego.demo.patterns.intermediate.AndMultipleRule;
import com.diego.demo.patterns.LowerHighLowerLow;
import com.diego.demo.strategies.entry.EntryRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.trading.rules.AndRule;

public class TweezerBottomsEntry extends EntryRule {

    private final Rule rule;

    public TweezerBottomsEntry(BarSeries series) {
        super(series);
        this.rule = rule(series);
    }

    private static Rule rule(BarSeries series) {
        return new ConsecutivePattern(
                new LowerHighLowerLow(series),
                new AndRule(
                        new LowerHighLowerLow(series),
                        new SupportedOnEMA(series)
                ),
                new AndMultipleRule(
                        new TweezerBottoms(series),
                        new EMAUptrend(series)
                )
        );
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        return rule.isSatisfied(index, tradingRecord);
    }
}
