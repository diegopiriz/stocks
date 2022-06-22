package com.diego.demo.patterns;

import com.diego.demo.patterns.intermediate.BaseRule;
import org.ta4j.core.BarSeries;
import org.ta4j.core.trading.rules.AndRule;

public class LowerHighLowerLow extends BaseRule {

    public LowerHighLowerLow(BarSeries series) {
        super(new AndRule(
                new LowerLow(series),
                new LowerHigh(series)
        ));
    }
}
