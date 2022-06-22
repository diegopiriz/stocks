package com.diego.demo.analysis;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.Comparator;

@Getter
public class Recommendation implements Comparable<Recommendation>{

    public final String symbol;
    public final double strongBuy;
    public final double buy;
    public final double hold;
    public final double sell;
    public final double strongSell;
    public final int total;
    public final double score;

    public Recommendation(String symbol, int strongBuy, int buy, int hold, int sell, int strongSell) {
        this.symbol = symbol;
        this.total = strongBuy + buy + hold + sell + strongSell;
        this.strongBuy = 1.0 * strongBuy / total;
        this.buy = 1.0 * buy / total;
        this.hold = 1.0 * hold / total;
        this.sell = 1.0 * sell / total;
        this.strongSell = 1.0 * strongSell / total;
        this.score = 1.0 * (strongBuy + 2*buy + 3*hold + 4*sell + 5*strongSell) / total;
    }

    @Override
    public int compareTo(Recommendation o) {
        return Comparator.comparing(Recommendation::getScore).reversed().compare(this, o);
    }

    public String toString() {
        return StringUtils.leftPad(symbol, 5) + ": " + f(score) + "(" + total + ")";
    }

    private String f(double v) {
        return new DecimalFormat("0.00").format(v);
    }
}
