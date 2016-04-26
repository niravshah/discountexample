package util.sequence;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static util.sequence.BigDecimalSequence.incrementingBy;

public class BigDecimalRange {

    private BigDecimalRange() {
    }

    BuilderStep toStep(final BigDecimal start, final BigDecimal step) {

        if (step.compareTo(ZERO) == 0) {
            throw new IllegalArgumentException("Cannot step in increments of zero");
        }

        return new BuilderStep() {
            @Override
            public BuilderStep stepping(double step) {
                return toStep(start, valueOf(step));
            }

            @Override
            public Range<BigDecimal> to(double end) {
                return new Range<BigDecimal>(new BigDecimalSequence(start, incrementingBy(step)), valueOf(end));
            }
        };
    }

    public static BuilderStep from(double from) {
        return new BigDecimalRange().toStep(valueOf(from), ONE);
    }

    public static Range<BigDecimal> upto(double to) {
        return new BigDecimalRange().toStep(ZERO, ONE).to(to);
    }

    public interface BuilderStep {
        BuilderStep stepping(double step);

        Range<BigDecimal> to(double to);
    }
}
