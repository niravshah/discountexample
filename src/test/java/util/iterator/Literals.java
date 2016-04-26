package util.iterator;

import java.util.LinkedHashMap;
import java.util.Map;

public class Literals {
    public static <S, T> TwoTuple<S, T> tuple(S first, T second) {
        return new TwoTuple<S, T>(first, second);
    }

    public static <K, V> MapBuilder<K, V> mapWith(K key, V value) {
        return new MapBuilder<K, V>().with(key, value);
    }

    public static <T> T[] asArray(T... items) {
        return items;
    }

    public static class MapBuilder<K, V> extends LinkedHashMap<K, V> {
        public MapBuilder<K, V> with(K key, V value) {
            return and(key, value);
        }

        public MapBuilder<K, V> and(K key, V value) {
            put(key, value);
            return this;
        }

        public Map<K, V> build() {
            return this;
        }
    }
}
