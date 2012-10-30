package util.sequence;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;

public class Range<T extends Comparable<T>> implements Iterable<T> {

    private final Sequence<T> sequence;
    private final T end;

    public Range(Sequence<T> sequence, T end) {
        checkArgument(end.compareTo(sequence.currentValue()) >= 0,
                String.format("End value %s must compare equal to or greater than %s", end, sequence.currentValue()));

        this.sequence = sequence;
        this.end = end;
    }

    @Override
    public Iterator<T> iterator() {
        return new SequenceIterator<T>(sequence, end);
    }
}
