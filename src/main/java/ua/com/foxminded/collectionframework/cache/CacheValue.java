package ua.com.foxminded.collectionframework.cache;

public class CacheValue<V> implements Comparable<CacheValue<V>> {

    private static final String NULL_ARGUMENT_MESSAGE = "Null argument is passed to comparison.";

    private V value;
    private int evictionPriority;

    public CacheValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public int getEvictionPriority() {
        return evictionPriority;
    }

    protected void setEvictionPriority(int evictionPriority) {
        this.evictionPriority = evictionPriority;
    }

    @Override
    public int compareTo(CacheValue<V> other) {
        if (other == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT_MESSAGE);
        }
        return this.evictionPriority - other.evictionPriority;
    }

    @Override
    public String toString() {
        return "\ncacheValue = " + value.toString() + "; evictionPriority = " + evictionPriority + "\n";
    }
}
