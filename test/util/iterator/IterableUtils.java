package util.iterator;

import com.google.common.collect.Iterables;
import util.model.Maybe;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.String.format;
import static java.util.Collections.shuffle;
import static util.iterator.Literals.tuple;
import static util.model.Maybe.maybe;
import static util.model.Maybe.nothing;
import static util.sequence.IntegerRange.from;

public class IterableUtils {

    private static final Random random = new Random();

    public static <T> Iterable<TwoTuple<Integer, T>> enumerate(Iterable<T> iterable) {
        return enumerate(iterable, 0);
    }

    public static <T> Iterable<TwoTuple<Integer, T>> enumerate(Iterable<T> iterable, Integer start) {
        return zip(from(start).to(MAX_VALUE), iterable);
    }

    public static <T> T sample(T... values) {
        return sample(newArrayList(values));
    }

    public static <T> T sample(Iterable<T> values) {
        return sample(1, values).iterator().next();
    }

    @SuppressWarnings("unchecked")
    public static <T> Collection<T> sample(Integer sampleSize, Iterable<T> candidates) {
        List<T> selected = materialise(take(cycle(newArrayList((T) null)), sampleSize));

        Iterable<TwoTuple<Integer, T>> enumerate = enumerate(candidates);
        Integer index = -1;
        for (TwoTuple<Integer, T> indexAndValue : enumerate) {
            index = indexAndValue.first();
            T candidate = indexAndValue.second();
            if (index < sampleSize) {
                selected.set(index, candidate);
            } else {
                Integer insertAt = random.nextInt(index + 1);
                if (insertAt < sampleSize) {
                    selected.set(insertAt, candidate);
                }
            }
        }

        if (index + 1 < sampleSize) {
            throw new IllegalArgumentException(format("Sample size %s exceeded candidate set size %s.", sampleSize, index + 1));
        }

        shuffle(selected);
        return selected;
    }

    public static <T> List<T> materialise(Iterable<T> iterable) {
        return newArrayList(iterable);
    }

    public static <T> Iterable<T> take(Iterable<T> iterable, Integer count) {
        return Iterables.limit(iterable, count);
    }

    public static <T> Iterable<T> cycle(Iterable<T> iterable) {
        return Iterables.cycle(iterable);
    }

    public static <T> Maybe<T> first(Iterable<? extends T> iterable) {
        try {
            return maybe(iterable.iterator().next());
        } catch (NoSuchElementException e) {
            return nothing();
        }
    }

    public static <T> Maybe<T> last(Iterable<T> iterable) {
        try {
            return maybe(Iterables.getLast(iterable));
        } catch (NoSuchElementException e) {
            return nothing();
        }
    }

    public static <T> TwoTuple<Collection<T>, T> splitLast(Iterable<T> iterable) {
        List<T> materialise = materialise(iterable);
        Collection<T> first = materialise.subList(0, materialise.size() - 1);
        T second = materialise.get(materialise.size() - 1);
        return tuple(first, second);
    }

    public static <T> Iterable<T> skip(Iterable<T> iterable, Integer numberToSkip) {
        return Iterables.skip(iterable, numberToSkip);
    }

    public static <S, T> Iterable<TwoTuple<S, T>> zip(final Iterable<S> firstIterable, final Iterable<T> secondIterable) {
        final Iterator<? extends S> firstIterator = firstIterable.iterator();
        final Iterator<? extends T> secondIterator = secondIterable.iterator();
        return new Iterable<TwoTuple<S, T>>() {

            @Override
            public Iterator<TwoTuple<S, T>> iterator() {
                return new Iterator<TwoTuple<S, T>>() {

                    @Override
                    public boolean hasNext() {
                        return firstIterator.hasNext() && secondIterator.hasNext();
                    }

                    @Override
                    public TwoTuple<S, T> next() {
                        return tuple(firstIterator.next(), secondIterator.next());
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public static <T extends Comparable<? super T>> Maybe<T> max(Iterable<T> values) {
        T max = null;
        for (T value : values) {
            if (max == null || value.compareTo(max) > 0) {
                max = value;
            }
        }
        return maybe(max);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> T max(T firstValue, T secondValue, T... otherValues) {
        Collection<T> candidates = newArrayList(firstValue, secondValue);
        candidates.addAll(newArrayList(otherValues));
        return max(candidates).value();
    }
}
