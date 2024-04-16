public class PointOfInterest implements Comparable<PointOfInterest> {
    private int id;
    private double x, y;
    private String name;

    public PointOfInterest(int id, double x, double y, String name) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getId() { return id; }
    public double getX() { return x; }
    public double getY() { return y; }
    public String getName() { return name; }

    @Override
    public int compareTo(PointOfInterest other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "PointOfInterest{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }
}
