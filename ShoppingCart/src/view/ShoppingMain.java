/*
 * TCSS 305 Assignment 2 - Shopping Cart
 */

package view;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import model.Item;
import utility.FileLoader;

/**
 * ShoppingMain provides the main method for a simple shopping cart GUI
 * displayer and calculator.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman (Formatting and Comments)
 * @author Alan Fowler (Numerous changes including use of BigDecimal and file input)
 * @author Charles Bryan (Added multiple file loading options)
 * @version Autumn 2015
 */

public final class ShoppingMain {
    
    /**
     * The filename of the file containing the items to display in the cart.
     */
    private static final String CONFIG_FILE = "config.txt";
    
    /**
     * The local path of the configuration files.
     */
    private static final String FILE_LOCATION = "files/";
    
    /**
     * The map that stores each campus name and the campus's bookstore inventory.
     */
    private static final Map<String, List<Item>> CAMPUS_INVINTORIES = new HashMap<>();   

    /**
     * A private constructor, to prevent external instantiation.
     */
    private ShoppingMain() {
    }

    /**
     * The main() method - displays and runs the shopping cart GUI.
     * 
     * @param theArgs Command line arguments, ignored by this program.
     */
    public static void main(final String... theArgs) {
        final List<String> campusNames = 
            FileLoader.readConfigurationFromFile(FILE_LOCATION + CONFIG_FILE);
        String mainCampus = null;
        for (final String campusName : campusNames) {
            //used to remove a warning and allow campusName to be final
            String alteredName = campusName;
            
            //as defined in config.txt the campus with a * should be the "main" campus
            if (campusName.charAt(0) == '*') {
                //remove the *
                alteredName = campusName.substring(1);
                mainCampus = alteredName;
            }
            CAMPUS_INVINTORIES.put(
                alteredName, 
                FileLoader.readItemsFromFile(FILE_LOCATION 
                    + alteredName.toLowerCase(Locale.ENGLISH) + ".txt"));
        }
        final String currentStore = mainCampus;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShoppingFrame(CAMPUS_INVINTORIES, currentStore);     
            }
        });
    } // end main()

} // end class ShoppingMain
