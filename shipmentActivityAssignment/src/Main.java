import java.util.ArrayDeque;
import java.util.Scanner;

class Container {
    private String id;
    private String description;
    private int weight;

    public Container(String id, String description, int weight) {
        this.id = id;
        this.description = description;
        this.weight = weight;
    }
    public String toString() {
        return id + " | " + description + " | " + weight + "kg";
    }
}

class Ship {
    private String name;
    private String captain;

    public Ship(String name, String captain) {
        this.name = name;
        this.captain = captain;
    }
    public String toString() {
        return name + " | Capt. " + captain;
    }
}

public class Main {
    private static ArrayDeque<Container> containerStack = new ArrayDeque<>();
    private static ArrayDeque<Ship> shipQueue = new ArrayDeque<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n -- Port Container Management System --");
            System.out.println("[1] Store container");
            System.out.println("[2] View port containers");
            System.out.println("[3] Register arriving ship");
            System.out.println("[4] View waiting ships");
            System.out.println("[5] Load next ship");
            System.out.println("[0] Exit");
            System.out.print("Choose an option: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> storeContainer();
                case 2 -> viewContainers();
                case 3 -> registerShip();
                case 4 -> viewShips();
                case 5 -> loadNextShip();
                case 0 -> System.out.println("Exiting system... Goodbye!");
                default -> System.out.println("Invalid choice, please try again.");
            }

        } while (choice != 0);
    }

    private static void storeContainer() {
        System.out.print("Enter container ID: ");
        String id = sc.nextLine();
        System.out.print("Enter description: ");
        String desc = sc.nextLine();
        System.out.print("Enter weight (kg): ");
        int weight = getIntInput();

        Container c = new Container(id, desc, weight);
        containerStack.push(c);
        System.out.println("Stored: " + c);
    }

    private static void viewContainers() {
        if (containerStack.isEmpty()) {
            System.out.println("No containers at the port.");
            return;
        }
        System.out.println("TOP →");
        for (Container c : containerStack) {
            System.out.println(c);
        }
        System.out.println("← BOTTOM");
    }

    private static void registerShip() {
        System.out.print("Enter ship name: ");
        String name = sc.nextLine();
        System.out.print("Enter captain name: ");
        String captain = sc.nextLine();

        Ship s = new Ship(name, captain);
        shipQueue.add(s);
        System.out.println("Registered: " + s);
    }

    private static void viewShips() {
        if (shipQueue.isEmpty()) {
            System.out.println("No ships waiting.");
            return;
        }
        System.out.println("FRONT →");
        for (Ship s : shipQueue) {
            System.out.println(s);
        }
        System.out.println("← REAR");
    }

    private static void loadNextShip() {
        if (containerStack.isEmpty() || shipQueue.isEmpty()) {
            System.out.println("Cannot load — either no containers or no ships available.");
            return;
        }
        Container c = containerStack.pop();
        Ship s = shipQueue.poll();
        System.out.println("Loaded: " + c + " → " + s);
        System.out.println("Remaining containers: " + containerStack.size());
        System.out.println("Remaining ships waiting: " + shipQueue.size());
    }

    private static int getIntInput() {
        while (true) {
            try {
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }
}
