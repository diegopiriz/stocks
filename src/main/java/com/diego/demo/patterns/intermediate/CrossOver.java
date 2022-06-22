package com.diego.demo.patterns.intermediate;

import com.diego.demo.patterns.intermediate.BaseRule;
import com.diego.demo.patterns.intermediate.OffsetedRule;
import org.ta4j.core.Indicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.trading.rules.AndRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;

public class CrossOver extends BaseRule {

    public CrossOver(Indicator<Num> first, Indicator<Num> second) {
        super(new AndRule(
                new OverIndicatorRule(first, second),
                new OffsetedRule(1, new OverIndicatorRule(second, first))
        ));
    }
}
