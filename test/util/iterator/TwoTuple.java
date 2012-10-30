package util.iterator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import static java.lang.String.format;

public class TwoTuple<S, T> {
    private S first;
    private T second;

    public TwoTuple(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public S first() {
        return first;
    }

    public T second() {
        return second;
    }

    public boolean equals(Object other) {
        if (other instanceof TwoTuple) {
            TwoTuple<?, ?> otherTuple = (TwoTuple<?, ?>) other;
            return new EqualsBuilder().append(first, otherTuple.first).append(second, otherTuple.second).isEquals();
        }
        return false;
    }

    public int hashCode() {
        return new HashCodeBuilder().append(first).append(second).toHashCode();
    }

    public String toString() {
        return format("(%s, %s)", first, second);
    }
}
