package com.diego.demo.patterns.intermediate;

import org.ta4j.core.Rule;
import org.ta4j.core.TradingRecord;

public abstract class BaseRule implements Rule {

    private final Rule baseRule;

    public BaseRule(Rule baseRule) {
        this.baseRule = baseRule;
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        return this.baseRule.isSatisfied(index, tradingRecord);
    }
}
