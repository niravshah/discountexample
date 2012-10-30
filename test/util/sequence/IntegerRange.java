package util.sequence;

import static util.sequence.IntegerSequence.incrementingBy;

public class IntegerRange {

    private IntegerRange() {
    }

    BuilderStep toStep(final int start, final int step) {

        if (step == 0) {
            throw new IllegalArgumentException("Cannot step in increments of zero");
        }

        return new BuilderStep() {
            @Override
            public BuilderStep stepping(int step) {
                return toStep(start, step);
            }

            @Override
            public Range<Integer> to(int end) {
                return new Range<Integer>(new IntegerSequence(start, incrementingBy(step)), end);
            }
        };
    }

    public static BuilderStep from(int from) {
        return new IntegerRange().toStep(from, 1);
    }

    public static Range<Integer> upto(int to) {
        return new IntegerRange().toStep(0, 1).to(to);
    }

    public interface BuilderStep {
        BuilderStep stepping(int step);

        Range<Integer> to(int to);
    }
}
