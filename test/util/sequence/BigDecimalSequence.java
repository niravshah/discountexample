package util.sequence;

import java.math.BigDecimal;

public class BigDecimalSequence extends AbstractSequence<BigDecimal> {

    public BigDecimalSequence(BigDecimal value, SequenceFunction<BigDecimal> function) {
        super(value, function);
    }

    @Override
    protected Sequence<BigDecimal> next(BigDecimal value, SequenceFunction<BigDecimal> function) {
        return new BigDecimalSequence(value, function);
    }

    public static SequenceFunction<BigDecimal> incrementingBy(final BigDecimal increment) {
        return new SequenceFunction<BigDecimal>() {
            @Override
            public BigDecimal next(BigDecimal current) {
                return current.add(increment);
            }
        };
    }

}
