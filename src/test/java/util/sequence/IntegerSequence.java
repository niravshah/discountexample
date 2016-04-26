package util.sequence;

public class IntegerSequence extends AbstractSequence<Integer> {

    public IntegerSequence(int value, SequenceFunction<Integer> function) {
        super(value, function);
    }

    @Override
    protected Sequence<Integer> next(Integer value, SequenceFunction<Integer> function) {
        return new IntegerSequence(value, function);
    }

    public static SequenceFunction<Integer> incrementingBy(final int increment) {
        return new SequenceFunction<Integer>() {
            @Override
            public Integer next(Integer current) {
                return current + increment;
            }
        };
    }
}
