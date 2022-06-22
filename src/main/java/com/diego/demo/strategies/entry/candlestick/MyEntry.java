package com.diego.demo.strategies.entry.candlestick;

import com.diego.demo.patterns.BullishPinBar;
import com.diego.demo.patterns.EMAUptrend;
import com.diego.demo.patterns.EmaCrossOverEma;
import com.diego.demo.patterns.FarFromUpperBBand;
import com.diego.demo.patterns.GreenCandle;
import com.diego.demo.patterns.HigherHighHigherLow;
import com.diego.demo.patterns.InsideCandle;
import com.diego.demo.patterns.LowerHighLowerLow;
import com.diego.demo.patterns.MacdCrossOverEma;
import com.diego.demo.patterns.NearToUpperBBand;
import com.diego.demo.patterns.SupportedOnEMA;
import com.diego.demo.patterns.WhiteSoldier;
import com.diego.demo.patterns.intermediate.AndMultipleRule;
import com.diego.demo.patterns.intermediate.ConsecutivePattern;
import com.diego.demo.patterns.intermediate.OrMultipleRule;
import com.diego.demo.strategies.entry.BigFall;
import com.diego.demo.strategies.entry.EntryRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.indicators.StochasticRSIIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsUpperIndicator;
import org.ta4j.core.indicators.helpers.PreviousValueIndicator;
import org.ta4j.core.trading.rules.AndRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

public class MyEntry extends EntryRule {
    private static final int EMA_FAST = 6;
    private static final int EMA_SLOW = 18;

    private static final int MACD_SHORT = 12;
    private static final int MACD_LONG = 26;
    private static final int MACD_EMA = 9;

    private static final int STOCHASTICS_PERIOD = 14;

    private final Rule rule;

    public MyEntry(BarSeries series) {
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
                new FarFromUpperBBand(series),
                new EmaCrossOverEma(series, EMA_FAST, EMA_SLOW),
                new MacdCrossOverEma(series, MACD_SHORT, MACD_LONG, MACD_EMA),
                new UnderIndicatorRule(new StochasticRSIIndicator(series, STOCHASTICS_PERIOD),0.8)
        );
        Rule I = new InsideCandle(series);
        Rule L = new LowerHighLowerLow(series);
        Rule B = new AndMultipleRule(
                new LowerHighLowerLow(series),
                new SupportedOnEMA(series)
        );
        Rule W = new WhiteSoldier(series);

        // NIO.
        // MacD crosses over, and very close to upper bb band
//         return new AndMultipleRule(
//                 new EMAUptrend(series),
//                 new ConsecutivePattern(
//                         new MacdCrossOverEma(series, MACD_SHORT, MACD_LONG, MACD_EMA),
//                         new MacdCrossOverEma(series, MACD_SHORT, MACD_LONG, MACD_EMA).negation()
//                 ),
//                 new NearToUpperBBand(series)
//         );

//        return new ConsecutivePattern(
//                new GreenCandle(series),
//                new GreenCandle(series),
//                new GreenCandle(series),
//                new GreenCandle(series),
//                new GreenCandle(series)
//        );

        //return new AndRule(new BigFall(series, 5), new SupportedOnEMA(series).negation());
        return new BigFall(series, 10);
    }
}
