package util.sequence;

public abstract class AbstractSequence<T> implements Sequence<T> {

    private final SequenceFunction<T> function;
    private final T value;

    AbstractSequence(T value, SequenceFunction<T> function) {
        this.function = function;
        this.value = value;
    }

    @Override
    public T currentValue() {
        return value;
    }

    @Override
    public Sequence<T> next() {
        return next(function.next(value), function);
    }

    protected abstract Sequence<T> next(T value, SequenceFunction<T> function);
}
