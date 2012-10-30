package util.iterator;

import java.util.Iterator;

public class IteratorUtils {
    public static <T> Iterable<T> asIterable(final Iterator<T> iterator) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return iterator;
            }
        };
    }
}
