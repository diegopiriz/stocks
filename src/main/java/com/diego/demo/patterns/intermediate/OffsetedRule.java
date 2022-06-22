package com.diego.demo.patterns.intermediate;

import org.ta4j.core.Rule;
import org.ta4j.core.TradingRecord;

public class OffsetedRule implements Rule {

    private final int offset;
    private final Rule rule;

    public OffsetedRule( int offset, Rule rule) {
        this.offset = offset;
        this.rule = rule;
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        int offsetedIndex = index - offset;
        if(offsetedIndex < 0) {
            return false;
        }
        return this.rule.isSatisfied(offsetedIndex, tradingRecord);
    }
}
