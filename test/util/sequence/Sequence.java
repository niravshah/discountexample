package util.sequence;

public interface Sequence<T> {
    T currentValue();
    Sequence<T> next();
}
