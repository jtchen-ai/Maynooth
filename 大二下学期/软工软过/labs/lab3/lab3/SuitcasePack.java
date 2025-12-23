import java.util.*;
import java.util.stream.Collectors;

// Class to represent an item with its properties
class Item {
    int id; // Item identifier
    double volume; // Volume in cm^3
    double worth; // Worth in Euros
    boolean divisible; // Can the item be split?
    double ratio; // Worth-to-volume ratio

    // Constructor
    Item(int id, double volume, double worth, boolean divisible) {
        this.id = id;
        this.volume = volume;
        this.worth = worth;
        this.divisible = divisible;
        if (volume > 0) {
            this.ratio = worth / volume;
        } else {
            this.ratio = Double.MAX_VALUE; // Effectively infinite ratio for zero-volume, positive-worth items
        }
    }

    // Getter for ratio
    double getRatio() {
        return ratio;
    }

    @Override
    public String toString() {
        return "Item{" +
               "id=" + id +
               ", volume=" + volume +
               ", worth=" + worth +
               ", divisible=" + divisible +
               ", ratio=" + String.format("%.4f", ratio) +
               '}';
    }
}

// Class to represent a suitcase
class Suitcase {
    int id; // Suitcase identifier
    double capacity; // Total capacity in cm^3
    double remainingCapacity; // Remaining capacity after assignments
    List<Assignment> assignments; // List of assigned items and their amounts

    // Constructor
    Suitcase(int id, double capacity) {
        this.id = id;
        this.capacity = capacity;
        this.remainingCapacity = capacity;
        this.assignments = new ArrayList<>();
    }

    // Add an item assignment to this suitcase
    // 'amount' here refers to the volume of the item being added
    void addAssignment(int itemId, double volumePacked) {
        if (volumePacked <= 1e-9) return; // Do not add if effectively zero volume

        assignments.add(new Assignment(itemId, volumePacked));
        this.remainingCapacity -= volumePacked;
        // Ensure remaining capacity doesn't go negative due to precision issues
        if (this.remainingCapacity < 1e-9) {
            this.remainingCapacity = 0;
        }
    }

    @Override
    public String toString() {
        return "Suitcase{" +
               "id=" + id +
               ", capacity=" + capacity +
               ", remainingCapacity=" + String.format("%.2f", remainingCapacity) +
               '}';
    }
}

// Class to represent an assignment of an item to a suitcase
class Assignment {
    int itemId; // Item assigned
    double volumePacked; // Volume of item assigned (full for indivisible, partial for divisible)

    // Constructor
    Assignment(int itemId, double volumePacked) {
        this.itemId = itemId;
        this.volumePacked = volumePacked;
    }

    @Override
    public String toString() {
        return "Assignment{" +
               "itemId=" + itemId +
               ", volumePacked=" + String.format("%.2f", volumePacked) +
               '}';
    }
}

public class SuitcasePack {

    public static void main(String[] args) {
        // Hardcoded sample test case as required by the assignment
        // This allows for easy testing without user input.

        // Sample inputs from the PDF:
        // The number of suitcases: 3
        // The capacity of suitcase 1 in 𝑐𝑚3: 90
        // The capacity of suitcase 2 in 𝑐𝑚3: 150
        // The capacity of suitcase 3 in 𝑐𝑚3: 100
        double[] suitcaseCapacities = {90.0, 150.0, 100.0};

        // The total number of items: 10
        // Item details: volume, worth, divisible (true/false)
        Item[] items = {
            new Item(1, 40, 10, true),   // Ratio: 0.25
            new Item(2, 50, 5, true),    // Ratio: 0.10
            new Item(3, 70, 5, false),   // Ratio: 0.0714
            new Item(4, 40, 5, true),    // Ratio: 0.125
            new Item(5, 80, 2, false),   // Ratio: 0.025
            new Item(6, 30, 5, true),    // Ratio: 0.1667
            new Item(7, 35, 20, true),   // Ratio: 0.5714 (Highest)
            new Item(8, 40, 5, false),   // Ratio: 0.125
            new Item(9, 73.5, 5, false), // Ratio: 0.0680
            new Item(10, 150, 5, false)  // Ratio: 0.0333
        };

        System.out.println("--- Input Suitcases ---");
        for(int i=0; i<suitcaseCapacities.length; i++){
            System.out.println("Suitcase " + (i+1) + " Capacity: " + suitcaseCapacities[i]);
        }
        System.out.println("\n--- Input Items ---");
        for(Item item : items){
            System.out.println(item);
        }
        System.out.println("\n--- Starting Packing Process ---");

        // Solve the packing problem
        solvePacking(suitcaseCapacities, items);
    }

    /**
     * Main method to solve the packing problem.
     * Implements a greedy strategy:
     * 1. Separates items into divisible and indivisible.
     * 2. Sorts indivisible items by worth/volume ratio (desc), then worth (desc) as tie-breaker.
     *    Attempts to pack them whole into the first available suitcase.
     * 3. Sorts divisible items by worth/volume ratio (desc).
     *    Distributes them into suitcases with remaining capacity.
     * 4. Outputs the assignment of items to suitcases and the total worth.
     *
     * @param suitcaseCapacities Array of capacities for each suitcase.
     * @param items Array of items to be packed.
     */
    static void solvePacking(double[] suitcaseCapacities, Item[] items) {
        // Create a map for easy lookup of item properties by ID later
        Map<Integer, Item> itemMap = new HashMap<>();
        for (Item item : items) {
            itemMap.put(item.id, item);
        }

        // Step 1: Initialize Suitcases
        List<Suitcase> suitcases = new ArrayList<>();
        for (int i = 0; i < suitcaseCapacities.length; i++) {
            suitcases.add(new Suitcase(i + 1, suitcaseCapacities[i]));
        }

        // Step 2: Separate items into divisible and indivisible
        List<Item> indivisibleItems = new ArrayList<>();
        List<Item> divisibleItems = new ArrayList<>();
        for (Item item : items) {
            if (item.divisible) {
                divisibleItems.add(item);
            } else {
                indivisibleItems.add(item);
            }
        }

        // Step 3: Sort items
        // Sort indivisible items: by ratio (desc), then by worth (desc) for tie-breaking
        indivisibleItems.sort((a, b) -> {
            int ratioCompare = Double.compare(b.getRatio(), a.getRatio());
            if (ratioCompare == 0) {
                return Double.compare(b.worth, a.worth); // Higher worth first if ratios are equal
            }
            return ratioCompare;
        });

        // Sort divisible items: by ratio (desc)
        divisibleItems.sort(Comparator.comparingDouble(Item::getRatio).reversed());
        
        System.out.println("\n--- Sorted Indivisible Items (by ratio desc, then worth desc) ---");
        indivisibleItems.forEach(System.out::println);
        System.out.println("\n--- Sorted Divisible Items (by ratio desc) ---");
        divisibleItems.forEach(System.out::println);


        // Step 4: Assign indivisible items (Greedy Heuristic)
        System.out.println("\n--- Packing Indivisible Items ---");
        for (Item item : indivisibleItems) {
            // Try to place in suitcases, in order of suitcase ID
            for (Suitcase suitcase : suitcases) {
                if (suitcase.remainingCapacity >= item.volume) {
                    System.out.println("Packing indivisible Item " + item.id + " (Vol: " + item.volume + ") into Suitcase " + suitcase.id);
                    suitcase.addAssignment(item.id, item.volume); // Pack the whole item
                    break; // Item placed, move to next indivisible item
                }
            }
        }

        // Step 5: Assign divisible items (Greedy for Fractional Knapsack)
        System.out.println("\n--- Packing Divisible Items ---");
        for (Item item : divisibleItems) {
            double volumeOfItemStillToPack = item.volume;
            System.out.println("Attempting to pack divisible Item " + item.id + " (Total Vol: " + item.volume + ", Ratio: " + String.format("%.4f",item.getRatio()) + ")");
            
            // Iterate through suitcases to distribute the current divisible item
            // This ensures higher-ratio items get priority for available space.
            for (Suitcase suitcase : suitcases) { // Process suitcases in their natural order (1, 2, 3...)
                if (volumeOfItemStillToPack <= 1e-9) { // Use a small epsilon for double comparison
                    break; // This item is fully packed
                }
                if (suitcase.remainingCapacity > 1e-9) {
                    double packableVolumeInSuitcase = Math.min(volumeOfItemStillToPack, suitcase.remainingCapacity);
                    if (packableVolumeInSuitcase > 1e-9) {
                        System.out.println("  Packing " + String.format("%.2f", packableVolumeInSuitcase) + " of Item " + item.id + " into Suitcase " + suitcase.id + " (Suitcase remaining: " + String.format("%.2f", suitcase.remainingCapacity) + ")");
                        suitcase.addAssignment(item.id, packableVolumeInSuitcase);
                        volumeOfItemStillToPack -= packableVolumeInSuitcase;
                    }
                }
            }
             if (volumeOfItemStillToPack > 1e-9) {
                System.out.println("  Could not fully pack Item " + item.id + ". Remaining volume: " + String.format("%.2f", volumeOfItemStillToPack));
            }
        }

        // Step 6: Output results
        System.out.println("\n--- Final Packing Results ---");
        double totalWorthPacked = 0;

        for (Suitcase suitcase : suitcases) {
            System.out.println("\nSuitcase " + suitcase.id + " (Capacity: " + suitcase.capacity + " cm^3, Final Remaining Capacity: " + String.format("%.2f", suitcase.remainingCapacity) + " cm^3)");
            if (suitcase.assignments.isEmpty()) {
                System.out.println("  Is empty.");
                continue;
            }
            double worthInSuitcase = 0;
            for (Assignment asgn : suitcase.assignments) {
                Item currentItem = itemMap.get(asgn.itemId);
                double packedPortionWorth;
                if (currentItem.divisible) {
                    // For divisible items, worth is proportional to the fraction of volume packed
                    packedPortionWorth = (asgn.volumePacked / currentItem.volume) * currentItem.worth;
                } else {
                    // For indivisible items, if packed, its full worth is gained.
                    // We assume asgn.volumePacked is equal to currentItem.volume if it was packed.
                    packedPortionWorth = currentItem.worth;
                }
                totalWorthPacked += packedPortionWorth;
                worthInSuitcase += packedPortionWorth;
                System.out.println("  - Item " + asgn.itemId + ": Volume Packed = " + String.format("%.2f", asgn.volumePacked) + " cm^3, " +
                                   "Contributed Worth = " + String.format("%.2f", packedPortionWorth) + " Euro " +
                                   "(Original: Vol=" + currentItem.volume + ", Worth=" + currentItem.worth + ", Divisible=" + currentItem.divisible + ")");
            }
            System.out.println("  Total worth in Suitcase " + suitcase.id + ": " + String.format("%.2f", worthInSuitcase) + " Euro");
        }

        System.out.println("\n==========================================================");
        System.out.println("Total value of items in all suitcases: " + String.format("%.2f", totalWorthPacked) + " Euro");
        System.out.println("==========================================================");
    }
}