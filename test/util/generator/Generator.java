package util.generator;

import java.util.Iterator;

public abstract class Generator<T> implements Iterator<T> {
    @Override public boolean hasNext() {
        return true;
    }

    @Override public void remove() {
        throw new UnsupportedOperationException();
    }
}
