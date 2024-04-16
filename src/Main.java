import java.util.Scanner;

public class Main {
    private static final POIManager poiManager = new POIManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Point of Interest Management System!");
        while (true) {
            System.out.println("\nAvailable Actions:");
            System.out.println("1 - Add a new POI");
            System.out.println("2 - Remove a POI by ID");
            System.out.println("3 - Search for POIs within a rectangular area");
            System.out.println("4 - Display all POIs");
            System.out.println("5 - Exit");
            System.out.print("Choose an action: ");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    addPOI();
                    break;
                case 2:
                    removePOI();
                    break;
                case 3:
                    searchPOI();
                    break;
                case 4:
                    displayAllPOIs();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid action! Please choose again.");
            }
        }
    }

    private static void addPOI() {
        System.out.println("Enter POI ID:");
        int id = scanner.nextInt();
        System.out.println("Enter X coordinate:");
        double x = scanner.nextDouble();
        System.out.println("Enter Y coordinate:");
        double y = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over
        System.out.println("Enter POI name:");
        String name = scanner.nextLine();

        PointOfInterest poi = new PointOfInterest(id, x, y, name);
        if (poiManager.addPOI(poi)) {
            System.out.println("POI added successfully!");
        } else {
            System.out.println("Failed to add POI. It may already exist.");
        }
    }

    private static void removePOI() {
        System.out.println("Enter the ID of the POI to remove:");
        int id = scanner.nextInt();
        if (poiManager.removePOI(id)) {
            System.out.println("POI removed successfully.");
        } else {
            System.out.println("No POI found with ID: " + id);
        }
    }

    private static void searchPOI() {
        System.out.println("Enter the top-left X coordinate:");
        double x1 = scanner.nextDouble();
        System.out.println("Enter the top-left Y coordinate:");
        double y1 = scanner.nextDouble();
        System.out.println("Enter the bottom-right X coordinate:");
        double x2 = scanner.nextDouble();
        System.out.println("Enter the bottom-right Y coordinate:");
        double y2 = scanner.nextDouble();

        poiManager.searchPOIs(x1, y1, x2, y2);
    }

    private static void displayAllPOIs() {
        poiManager.displayAll();
    }
}
