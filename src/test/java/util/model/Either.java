package util.model;

import static java.lang.String.format;

public class Either<S, T> {
    private final S firstValue;
    private final T secondValue;

    private Either(S firstValue, T secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <S, T> Either<S, T> either(Class<S> clazz, T secondValue) {
        return new Either<S, T>(null, secondValue);
    }

    public static <S, T> Either<S, T> either(S firstValue, Class<T> clazz) {
        return new Either<S, T>(firstValue, null);
    }

    public Boolean hasFirstValue() {
        return firstValue != null;
    }

    public Boolean hasSecondValue() {
        return secondValue != null;
    }

    public S getFirstValue() {
        if (hasFirstValue()) {
            return firstValue;
        } else {
            throw new IllegalAccessError("Either has no first value.");
        }
    }

    public T getSecondValue() {
        if (hasSecondValue()) {
            return secondValue;
        } else {
            throw new IllegalAccessError("Either has no second value.");
        }
    }

    @Override public String toString() {
        if (hasFirstValue()) {
            return format("Either.firstValue(%s)", getFirstValue());
        } else {
            return format("Either.secondValue(%s)", getSecondValue());
        }
    }
}
