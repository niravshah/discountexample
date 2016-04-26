package util.sequence;

public interface SequenceFunction<T> {
    T next(T current);
}
