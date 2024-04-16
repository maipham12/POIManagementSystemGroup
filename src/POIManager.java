public class POIManager {
    private POIList poiList;   // List to hold POIs for sequential access
    private POIAVLTree poiTree; // AVL tree for efficient searching

    public POIManager() {
        this.poiList = new POIList();
        this.poiTree = new POIAVLTree();
    }

    public boolean addPOI(PointOfInterest poi) {
        if (!poiTree.contains(poi)) {
            poiTree.add(poi);
            poiList.insertAt(poiList.size(), poi);  // Insert at the end of the list
            return true;
        }
        return false;
    }

    public boolean removePOI(int id) {
        PointOfInterest target = new PointOfInterest(id, 0, 0, "");  // Dummy POI for searching
        if (poiTree.contains(target)) {
            poiTree.remove(target);
            poiList.remove(target);
            return true;
        }
        return false;
    }

    public void searchPOIs(double x1, double y1, double x2, double y2) {
        poiList.reset();
        boolean found = false;
        while (poiList.hasNext()) {
            PointOfInterest poi = poiList.next();
            if (poi.getX() >= x1 && poi.getX() <= x2 && poi.getY() >= y1 && poi.getY() <= y2) {
                System.out.println(poi);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No POIs found in the specified area.");
        }
    }

    public void displayAll() {
        poiList.reset();
        boolean found = false;
        while (poiList.hasNext()) {
            PointOfInterest poi = poiList.next();
            System.out.println(poi);
            found = true;
        }
        if (!found) {
            System.out.println("No POIs to display.");
        }
    }
}
