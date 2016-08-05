/*
 * Todd Crane
 * TCSS 305
 * assignment 4 - snapshop
 */

package gui;

import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.Filter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;

import image.PixelImage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * The graphical user interface for the SnapShop program.
 * 
 * @author Todd Crane
 * @version 4/30/2016
 */
public class SnapShopGUI {
    
    /** The frame for this GUI. */
    private final JFrame myFrame = new JFrame();
    
    /** File chooser for opening and saving files. */
    private final JFileChooser myChooser = new JFileChooser();
    
    /** Label for the image. */
    private final JLabel myImageLabel = new JLabel(new ImageIcon());

    /** Panel for the filter buttons. */
    private final JPanel myFilterPanel = new JPanel(new GridLayout(7, 1));
    
    /** Panel for the menu buttons. */
    private final JPanel myMenuPanel = new JPanel();
    
    /** The default directory this program will open to.*/
    private File myDirectory = new File("sample_images");
    
    /** Image to be displayed. */
    private PixelImage myImage;
    
    /** 
     * Array of all current filters. 
     * Add new filters here.
     * New filters added will automatically have buttons created.
     */
    private final Filter[] myFilters = new Filter[] {
        new EdgeDetectFilter(), new EdgeHighlightFilter(),
        new FlipHorizontalFilter(), new FlipVerticalFilter(),
        new GrayscaleFilter(), new SharpenFilter(),
        new SoftenFilter()};
    
    /**
     * Array for all current menu buttons.
     * Add new menu buttons here.
     * Method to create new menu buttons will need implementation.
     */
    private final JButton[] myMenuButtons = new JButton[] {
        new JButton("Open..."), new JButton("Save As..."),
        new JButton("Close Image")};    
    
    /**
     * Constructs the buttons for the GUI.
     * Uses anonymous inner classes. 
     */
    protected SnapShopGUI() {
        super();
        createButtons();
    }
    
    /** 
     * Creates buttons from the filters present in the array,
     * and creates the menu buttons.
     */
    private void createButtons() {
        //Create filter buttons.
        for (int i = 0; i < myFilters.length; i++) {
            myFilterPanel.add(createFilterButton(myFilters[i]));
        }
        //Create menu buttons.
        myMenuPanel.add(createOpenButton());
        myMenuPanel.add(createSaveButton());
        myMenuPanel.add(createCloseImageButton());
    }
    
    /**
     * Creates a filter button and attaches an action listener.
     * Connects the button to the correct filter effect.
     * 
     * @param theObject The filter object to be applied to the button.
     * @return The filter button.
     */
    private JButton createFilterButton(final Filter theObject) {
        //Create the button by calling the filter class name.
        final JButton button = new JButton(theObject.getDescription());
        //Attach action listener, anonymous class connects to filter effect.
        button.addActionListener(new ActionListener() {
            //Method to attach the filter effect.
            public void actionPerformed(final ActionEvent theEvent) {
                theObject.filter(myImage); //Apply correct filter effect.
                myImageLabel.setIcon(new ImageIcon(myImage)); //Update the image.
            }
        });
        return button;
    }
    
    /**
     * Creates the open button and attaches an action listener.
     * Connects the button to the file chooser.
     * 
     * @return The open button.
     */
    private JButton createOpenButton() {
        //Attach action listener, anonymous class connects to file chooser.
        myMenuButtons[0].addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myChooser.setCurrentDirectory(myDirectory);               
                final int result = myChooser.showOpenDialog(myFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    myDirectory = myChooser.getCurrentDirectory();
                    try {
                        myImage = PixelImage.load(myChooser.getSelectedFile());
                        myImageLabel.setIcon(new ImageIcon(myImage));
                        enableButton(myFilterPanel, true); //Enable filter buttons.
                        enableButton(myMenuPanel, true); //Enable menu buttons.
                        myFrame.pack(); //Resize the window to fit the image.
                    } catch (final IOException e) {
                        JOptionPane.showMessageDialog(myFrame, 
                            "The selected file did not contain an image!",
                            "Open Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        return myMenuButtons[0];
    }
    
    /**
     * Creates the save button and action listener.
     * Connects the button to the save dialog.
     * 
     * @return The save button.
     */
    private JButton createSaveButton() {
        /* Attach action listener, anonymous class connects to save dialog. */
        myMenuButtons[1].addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                saveCheck();
            }
        });
        return myMenuButtons[1];
    }
    
    /**
     * Helper method for createSaveButton.
     * Checks whether or not the file already exists.
     * Provides yes, no, and cancel options.
     * Yes will overwrite the existing file, no will return to the save dialog,
     * cancel will close the save dialog.
     */
    private void saveCheck() {
        final int saveResult = myChooser.showSaveDialog(myFrame);
        final File file = myChooser.getSelectedFile();
        
        //If the file exists, prompt user.
        if (saveResult == JFileChooser.APPROVE_OPTION && file.exists()) {
            final int overwriteResult = JOptionPane.showConfirmDialog(myFrame, 
                "The file already exists, overwrite?", "Existing File ", 
                JOptionPane.YES_NO_CANCEL_OPTION);
           //Deal with user selection on the overwrite prompt.
            if (overwriteResult == JOptionPane.YES_OPTION) {
                saveFile(file);
            } else if (overwriteResult == JOptionPane.NO_OPTION) {
                saveCheck();
            }
        //If the file doesn't exist, save.
        } else if (saveResult == JFileChooser.APPROVE_OPTION && !file.exists()) {
            saveFile(file);
        }
    }
    
    /**
     * Helper method for saveCheck.
     * Will save the file.
     * 
     * @param theFile The file to be saved.
     */
    private void saveFile(final File theFile) {
        try {
            myImage.save(theFile);
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(myFrame, 
                "Failed to save!",
                "Save Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Creates the close image button and action listener.
     * Connects the button to close image action.
     * 
     * @return The close image button.
     */
    private JButton createCloseImageButton() {
        //Attach action listener, anonymous class connects to save dialog.
        myMenuButtons[2].addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                //Set the image to a blank image icon.
                myImageLabel.setIcon(new ImageIcon());
                //Disable the appropriate buttons and resize frame.
                enableButton(myFilterPanel, false);
                myMenuButtons[1].setEnabled(false);
                myMenuButtons[2].setEnabled(false);
                myFrame.pack();
            }
        });
        return myMenuButtons[2];
    }
    
    /**
     * Method for enabling/disabling all filter buttons.
     * Takes a boolean to determine whether to enable or disable.
     * False will disable, true will enable.
     * 
     * @param thePanel The panel containing the filter buttons.
     * @param theBool Whether or not to enable or disable.
     */
    private void enableButton(final JPanel thePanel, final boolean theBool) {
        //Go through the buttons in the panel.
        for (int i = 0; i < thePanel.getComponentCount(); i++) {
            if (theBool) { //True will set to true.
                thePanel.getComponent(i).setEnabled(true);
            } else { //False will set to false. 
                thePanel.getComponent(i).setEnabled(false);
            }
        }
    }
    
    /**
     * Sets up and displays the GUI.
     */
    public void start() {
        //Add the panels to the frame.
        myFrame.add(myFilterPanel, BorderLayout.WEST);
        myFrame.add(myMenuPanel, BorderLayout.SOUTH);
        myFrame.add(myImageLabel, BorderLayout.CENTER);
        //Set up the frame correctly.
        myFrame.setTitle("TCSS 305 SnapShop");
        myFrame.setResizable(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        //Disable the filter and menu buttons on startup.
        enableButton(myFilterPanel, false);
        myMenuButtons[1].setEnabled(false);
        myMenuButtons[2].setEnabled(false);
        //Set to visible last.
        myFrame.setVisible(true);
    }
}