/*
 * TCSS 305 Assignment 2 - Shopping Cart
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
//import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
//import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Item;
import model.ItemOrder;
import model.ShoppingCart;

/**
 * ShoppingFrame provides the user interface for a shopping cart program.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman (Formatting and Comments)
 * @author Alan Fowler (Numerous changes to code and comments including use of BigDecimal)
 * @author Charles Bryan (Added radio buttons to change campus locations)
 * @version Autumn 2015
 */
public final class ShoppingFrame extends JFrame {
    
    /**
     * The Serialization ID.
     */
    private static final long serialVersionUID = 0;
    
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /**
     * The width of the text field in the GUI.
     */
    private static final int TEXT_FIELD_WIDTH = 12;
    
    /*
     * For the UW color palette and other UW branding information see
     * http://www.washington.edu/marketing/files/2012/09/WebColorPalette1.pdf 
     */
    
    /**
     * The color for some elements in the GUI.
     */
    private static final Color COLOR_1 = new Color(199, 153, 0); // UW Gold

    /**
     * The color for some elements in the GUI.
     */
    private static final Color COLOR_2 = new Color(57, 39, 91); // UW Purple

    /**
     * The shopping cart used by this GUI.
     */
    private final ShoppingCart myItems;
    
    /**
     * The map that stores each campus name and the campus's bookstore inventory.
     */
    private final Map<String, List<Item>> myCampusInventories; 

    /**
     * The map that stores each campus name and the campus's bookstore inventory.
     */
    private final String myCurrentCampus;
    
    /**
     * The text field used to display the total amount owed by the customer.
     */
    private final JTextField myTotal;
    
    /**
     * The panel that holds the item descriptions. Needed to add and remove on
     * the fly when the radio buttons change. 
     */
//    private JPanel myItemsPanel;

    /**
     * A List of the item text fields.
     */
    private final List<JTextField> myQuantities;
    
    /**
     * Initializes the shopping cart GUI.
     * 
     * @param theCampusInventories The list of items.
     * @param theCurrentCampus The campus that is originally selected when 
     * the application starts. 
     */
    public ShoppingFrame(final Map<String, List<Item>> theCampusInventories, 
                         final String theCurrentCampus) {
        // create frame and order list
        super(); // No title on the JFrame. We can set this later.
        
        myItems = new ShoppingCart();

        // set up text field with order total
        myTotal = new JTextField("$0.00", TEXT_FIELD_WIDTH);
        
        myQuantities = new LinkedList<>();
        
        myCampusInventories = theCampusInventories;
        myCurrentCampus = theCurrentCampus;
        
        setupGUI();
    }    

    /**
     * Setup the various parts of the GUI.
     * 
     */
    private void setupGUI() {
        // hide the default JFrame icon
        //final Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
        
        
        // replace the default JFrame icon
        final ImageIcon img = new ImageIcon("files/w.gif");
        setIconImage(img.getImage());
        
        setTitle("Shopping Cart");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // the following was used in autumn 2015, not in winter 2016
        add(makeTotalPanel(), BorderLayout.NORTH);
        
        final JPanel itemsPanel = makeItemsPanel(myCampusInventories.get(myCurrentCampus)); 
        add(itemsPanel, BorderLayout.CENTER);
        
        add(makeCheckBoxPanel(), BorderLayout.SOUTH);

        // adjust size to just fit
        pack();
        
        // make the GUI so that it cannot be resized by the user dragging a corner
        setResizable(false);
        
        // position the frame in the center of the screen
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                    SCREEN_SIZE.height / 2 - getHeight() / 2);
        setVisible(true);
    }
    
//    /**
//     * Creates the panel to hold the campus location radio buttons. 
//     * 
//     * @return The created JPanel
//     */
//    private JPanel makeCampusPanel() {
//        final JPanel p = new JPanel();
//        p.setBackground(COLOR_2);
//        
//        final ButtonGroup g = new ButtonGroup();
//        for (final Object campus : myCampusInventories.keySet()) {
//            final JRadioButton rb = new JRadioButton(campus.toString());
//            rb.setForeground(Color.WHITE);
//            rb.setBackground(COLOR_2);
//            rb.setSelected(campus.equals(myCurrentCampus));
//            g.add(rb);
//            p.add(rb);
//            
//            //Java 8 use of Lambda Expression instead 
//            // of an anonymous inner ActionListener object
//            rb.addActionListener(ae -> {
//                    myCurrentCampus = rb.getText();
//                    
//                    //remove the old panel and add the new one
//                    remove(myItemsPanel);
//                    myItemsPanel = makeItemsPanel(myCampusInventories.get(myCurrentCampus));
//                    add(myItemsPanel, BorderLayout.CENTER);
//                    
//                    //clear previous data from the ShppingCart and
//                    //update the total in the GUI
//                    myItems.clear();
//                    updateTotal();
//                    
//                    //redraw the UI with the new panel
//                    pack();
//                    revalidate();
//                } 
//            );
//        } 
//        return p;
//    }

    /**
     * Creates a panel to hold the total.
     * 
     * @return The created panel
     */
    private JPanel makeTotalPanel() {
        // tweak the text field so that users can't edit it, and set
        // its color appropriately

        myTotal.setEditable(false);
        myTotal.setEnabled(false);
        myTotal.setDisabledTextColor(Color.BLACK);

        // create the panel, and its label

        final JPanel totalPanel = new JPanel();
        totalPanel.setBackground(COLOR_2);
        final JLabel l = new JLabel("order total");
        l.setForeground(Color.WHITE);
        totalPanel.add(l);
        totalPanel.add(myTotal);
        
        final JPanel p = new JPanel(new BorderLayout());
        //p.add(makeCampusPanel(), BorderLayout.NORTH);
        p.add(totalPanel, BorderLayout.CENTER);
        
        return p;
    }

    /**
     * Creates a panel to hold the specified list of items.
     * 
     * @param theItems The items
     * @return The created panel
     */
    private JPanel makeItemsPanel(final List<Item> theItems) {
        final JPanel p = new JPanel(new GridLayout(theItems.size(), 1));
        
        for (final Item item : theItems) {
            addItem(item, p);
        }

        return p;
    }

    /**
     * Creates and returns the checkbox panel.
     * 
     * @return the checkbox panel
     */
    private JPanel makeCheckBoxPanel() {
        final JPanel p = new JPanel();
        p.setBackground(COLOR_2);
        
        final JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myItems.clear();
                for (final JTextField field : myQuantities) {
                    field.setText("");
                }
                updateTotal();
            }
        });
        p.add(clearButton);
        
        final JCheckBox cb = new JCheckBox("customer has store membership");
        cb.setForeground(Color.WHITE);
        cb.setBackground(COLOR_2);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myItems.setMembership(cb.isSelected());
                updateTotal();
            }
        });
        p.add(cb);
        
        return p;
    }

    /**
     * Adds the specified product to the specified panel.
     * 
     * @param theItem The product to add.
     * @param thePanel The panel to add the product to.
     */
    private void addItem(final Item theItem, final JPanel thePanel) {
        final JPanel sub = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sub.setBackground(COLOR_1);
        final JTextField quantity = new JTextField(3);
        myQuantities.add(quantity);
        quantity.setHorizontalAlignment(SwingConstants.CENTER);
        quantity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                quantity.transferFocus();
            }
        });
        quantity.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent theEvent) {
                updateItem(theItem, quantity);
            }
        });
        sub.add(quantity);
        final JLabel l = new JLabel(theItem.toString());
        l.setForeground(COLOR_2);
        sub.add(l);
        thePanel.add(sub);
    }

    /**
     * Updates the set of items by changing the quantity of the specified
     * product to the specified quantity.
     * 
     * @param theItem The product to update.
     * @param theQuantity The new quantity.
     */
    private void updateItem(final Item theItem, final JTextField theQuantity) {
        final String text = theQuantity.getText().trim();
        int number;
        try {
            number = Integer.parseInt(text);
            if (number < 0) {
                // disallow negative numbers
                throw new NumberFormatException();
            }
        } catch (final NumberFormatException e) {
            number = 0;
            theQuantity.setText("");
        }
        myItems.add(new ItemOrder(theItem, number));
        updateTotal();
    }

    /**
     * Updates the total displayed in the window.
     */
    private void updateTotal() {
        final double total = myItems.calculateTotal().doubleValue();
        myTotal.setText(NumberFormat.getCurrencyInstance().format(total));
    }
}

// end of class ShoppingFrame
