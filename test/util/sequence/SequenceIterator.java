package util.sequence;

import java.util.Iterator;

class SequenceIterator<T extends Comparable<T>> implements Iterator<T> {

    private Sequence<T> sequence;
    private final T end;

    SequenceIterator(Sequence<T> sequence, T end) {
        this.sequence = sequence;
        this.end = end;
    }

    @Override
    public boolean hasNext() {
        return sequence.currentValue().compareTo(end) <= 0;
    }

    @Override
    public T next() {
        T value = sequence.currentValue();
        sequence = sequence.next();
        return value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
