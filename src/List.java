public interface List<T> {
    boolean insertAt(int index, T value);
    boolean insertBefore(T searchValue, T value);
    boolean insertAfter(T searchValue, T value);
    boolean removeAt(int index);
    boolean remove(T value);
    boolean contains(T value);
    int size();
    boolean hasNext();
    T next();
    void reset();
    T get(int index);
}
