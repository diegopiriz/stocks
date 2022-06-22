package com.diego.demo.patterns.intermediate;

import org.ta4j.core.Rule;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConsecutivePattern extends BaseRule {

    public ConsecutivePattern(Rule... rules) {
        this(Arrays.asList(rules));
    }

    public ConsecutivePattern(List<Rule> rules) {
        super(concatRules(rules));
    }

    private static Rule concatRules(List<Rule> rules) {
        List<Rule> offseted = new LinkedList<>();
        for (int i = 0; i < rules.size(); i++) {
            offseted.add(new OffsetedRule(rules.size() - i - 1, rules.get(i)));
        }
        return new AndMultipleRule(offseted);
    }
}
