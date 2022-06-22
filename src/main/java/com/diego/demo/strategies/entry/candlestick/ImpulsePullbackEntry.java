package com.diego.demo.strategies.entry.candlestick;

import com.diego.demo.patterns.intermediate.ConsecutivePattern;
import com.diego.demo.patterns.EMAUptrend;
import com.diego.demo.patterns.intermediate.AndMultipleRule;
import com.diego.demo.patterns.EmaCrossOverEma;
import com.diego.demo.patterns.HigherHighHigherLow;
import com.diego.demo.patterns.InsideCandle;
import com.diego.demo.patterns.LowerHighLowerLow;
import com.diego.demo.patterns.MacdCrossOverEma;
import com.diego.demo.patterns.intermediate.OrMultipleRule;
import com.diego.demo.strategies.entry.EntryRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.trading.rules.AndRule;

public class ImpulsePullbackEntry extends EntryRule {

    private static final int EMA_FAST = 6;
    private static final int EMA_SLOW = 18;

    private static final int MACD_SHORT = 12;
    private static final int MACD_LONG = 26;
    private static final int MACD_EMA = 9;

    private final Rule rule;

    public ImpulsePullbackEntry(BarSeries series) {
        super(series);
        this.rule = rule(series);
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        return rule.isSatisfied(index, tradingRecord);
    }

    private static Rule rule(BarSeries series) {
        Rule H = new HigherHighHigherLow(series);
        Rule T = new AndMultipleRule(
                new HigherHighHigherLow(series),
                new EmaCrossOverEma(series, EMA_FAST, EMA_SLOW),
                new MacdCrossOverEma(series, MACD_SHORT, MACD_LONG, MACD_EMA)
        );
        Rule I = new InsideCandle(series);
        Rule L = new LowerHighLowerLow(series);
        return new AndRule(
                new EMAUptrend(series),
                new OrMultipleRule(
                        new ConsecutivePattern(H, T, L),
                        new ConsecutivePattern(H, T, L, I),
                        new ConsecutivePattern(H, T, L, L),
                        new ConsecutivePattern(H, T, I, L),
                        new ConsecutivePattern(H, T, L, I, I),
                        new ConsecutivePattern(H, T, L, L, I),
                        new ConsecutivePattern(H, T, L, I, L),
                        new ConsecutivePattern(H, T, I, L, L),
                        new ConsecutivePattern(H, T, I, L, I)
                )
        );
    }
}
