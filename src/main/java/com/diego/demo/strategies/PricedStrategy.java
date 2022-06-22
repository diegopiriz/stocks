package com.diego.demo.strategies;

import com.diego.demo.strategies.entry.EntryRule;
import com.diego.demo.strategies.exit.ExitRule;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;

public class PricedStrategy extends BaseStrategy {

    private final EntryRule entry;
    private final ExitRule exit;

    public PricedStrategy(EntryRule entry, ExitRule exit) {
        super(entry, exit);
        this.entry = entry;
        this.exit = exit;
    }

    public Num getEntryPrice(int index) {
        return entry.getEntryPrice(index);
    }

    public Num getExitPrice(int index, TradingRecord tradingRecord) {
        return exit.getExitPrice(index, tradingRecord);
    }

    @Override
    public EntryRule getEntryRule() {
        return entry;
    }

    @Override
    public ExitRule getExitRule() {
        return exit;
    }

    public String toString() {
        return "Priced Strategy: \n" +
                "\tEntry: " + entry.toString() + "\n" +
                "\tExit:  " + exit.toString() + "\n\n";
    }
}
