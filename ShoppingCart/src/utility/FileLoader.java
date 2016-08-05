package utility;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import model.Item;

/**
 * A utility class for The shopping cart application. 
 * 
 * @author Charles Bryan
 * @version Autumn 2015
 */
public final class FileLoader {
    
    /**
     * A private constructor, to prevent external instantiation.
     */
    private FileLoader() {
        
    }
    /**
     * Reads item information from a file and returns a List of Item objects.
     * @param theFile the name of the file to load into a List of Items
     * @return a List of Item objects created from data in an input file
     */
    public static List<Item> readItemsFromFile(final String theFile) {
        final List<Item> items = new LinkedList<>();
        
        try (Scanner input = new Scanner(Paths.get(theFile))) { // Java 7!
            while (input.hasNextLine()) {
                final Scanner line = new Scanner(input.nextLine());
                line.useDelimiter(";");
                final String itemName = line.next();
                final BigDecimal itemPrice = line.nextBigDecimal();
                if (line.hasNext()) {
                    final int bulkQuantity = line.nextInt();
                    final BigDecimal bulkPrice = line.nextBigDecimal();
                    items.add(new Item(itemName, itemPrice, bulkQuantity, bulkPrice));
                } else {
                    items.add(new Item(itemName, itemPrice));
                }
                line.close();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } // no finally block needed to close 'input' with the Java 7 try with resource block
    
        return items;
    }
    
    /**
     * Reads item information from a file and returns a List of Item objects.
     * @param theFile the name of the file to load into a List of Items
     * @return a List of Item objects created from data in an input file
     */
    public static List<String> readConfigurationFromFile(final String theFile) {
        final List<String> results = new LinkedList<>();
        
        try (Scanner input = new Scanner(Paths.get(theFile))) { // Java 7!
            while (input.hasNextLine()) {
                final String line = input.nextLine();
                
                //if (!line.startsWith("#")) {
                if (!(line.charAt(0) == '#')) {
                    results.add(line);
                }
                
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } // no finally block needed to close 'input' with the Java 7 try with resource block
    
        return results;
    }    
}
