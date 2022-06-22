package com.diego.demo.patterns.intermediate;

import org.ta4j.core.Rule;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.trading.rules.AbstractRule;

import java.util.Arrays;
import java.util.List;

public class AndMultipleRule extends AbstractRule {

    private final List<Rule> rules;

    public AndMultipleRule(Rule... rules) {
        this(Arrays.asList(rules));
    }

    public AndMultipleRule(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        return isSatisfied(this.rules, index, tradingRecord);
    }

    public boolean isSatisfied(List<Rule> rules, int index, TradingRecord tradingRecord) {
        if(rules.size() == 0) {
            return true;
        }
        if(rules.size() == 1) {
            return rules.get(0).isSatisfied(index, tradingRecord);
        }
        return rules.get(0).isSatisfied(index, tradingRecord) && isSatisfied(rules.subList(1, rules.size()), index, tradingRecord);
    }
}
