package util.model;

import static java.lang.String.format;

public class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    public static <T> Maybe<T> nothing() {
        return new Maybe<T>(null);
    }

    public static <T> Maybe<T> nothing(Class<T> cl) {
        return new Maybe<T>(null);
    }

    public static <T> Maybe<T> maybe(T value) {
        return new Maybe<T>(value);
    }

    public boolean hasValue() {
        return value != null;
    }

    public T value() {
        if (hasValue()) {
            return value;
        } else {
            throw new IllegalAccessError("Maybe has no value.");
        }
    }

    public T valueOrDefault(T defaultValue) {
        if (hasValue()) {
            return value;
        } else {
            return defaultValue;
        }
    }

    @Override
    public String toString() {
        if (hasValue()) {
            return format("Maybe.value(%s)", value);
        } else {
            return "Maybe.nothing()";
        }
    }
}
