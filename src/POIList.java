public class POIList implements List<PointOfInterest> {
    private PointOfInterest[] elements;
    private int size;
    private int iteratorIndex;

    public POIList() {
        elements = new PointOfInterest[10]; // Initial capacity
        size = 0;
        iteratorIndex = 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            PointOfInterest[] newElements = new PointOfInterest[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    @Override
    public boolean insertAt(int index, PointOfInterest value) {
        if (index < 0 || index > size) {
            return false;
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
        return true;
    }

    @Override
    public boolean insertBefore(PointOfInterest searchValue, PointOfInterest value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].compareTo(searchValue) == 0) {
                return insertAt(i, value);
            }
        }
        return false;
    }

    @Override
    public boolean insertAfter(PointOfInterest searchValue, PointOfInterest value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].compareTo(searchValue) == 0) {
                return insertAt(i + 1, value);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return true;
    }

    @Override
    public boolean remove(PointOfInterest value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].compareTo(value) == 0) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean contains(PointOfInterest value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].compareTo(value) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean hasNext() {
        return iteratorIndex < size;
    }

    @Override
    public PointOfInterest next() {
        if (!hasNext()) {
            return null;
        }
        return elements[iteratorIndex++];
    }

    @Override
    public void reset() {
        iteratorIndex = 0;
    }

    @Override
    public PointOfInterest get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return elements[index];
    }
}
