/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A class to create all menu items and set up listeners.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class MenuBar extends JMenuBar implements PropertyChangeListener {
   
    /** A generated serialization ID. */
    private static final long serialVersionUID = -3923760330567422182L;

    /** The text to be displayed on the about window. */
    private static final String ABOUT = "TCSS 305 PowerPaint\n"
                    + "Spring 2016\nTodd Crane";
    
    /** The slider extent setting. */
    private static final int SLIDER_EXTENT = 1;
    
    /** The slider minor tick spacing. */
    private static final int SLIDER_MINOR = 1;
    
    /** The slider major tick spacing. */
    private static final int SLIDER_MAJOR = 5;
    
    /** The slider min value. */
    private static final int SLIDER_MIN = 0;
    
    /** The slider max value. */
    private static final int SLIDER_MAX = 20;
    
    /** The slider value. */
    private static final int SLIDER_VALUE = 1;
    
    /** Menu button for quitting the application. */
    private final JMenuItem myQuit;
    
    /** Menu button for clearing the drawing space. */
    private final JMenuItem myClear;
    
    /** Slider for setting the stroke thickness. */
    private final JSlider mySlider;
    
    /** Menu button for selecting the stroke color. */
    private final JMenuItem myDrawColor;
    
    /** Menu button for selecting the fill color. */
    private final JMenuItem myFillColor;
    
    /** Check-box for setting fill for shapes. */
    private final JCheckBoxMenuItem myFillCheckBox;
    
    /** Menu for holding the tool bar buttons. */
    private final JMenu myToolsMenu;
    
    /** Group for the tool bar buttons. */
    private final ButtonGroup myGroup;
    
    /** Menu button for opening the about window. */
    private final JMenuItem myAbout;
    
    /** The panel for drawing shapes. */
    private DrawingPanel myDrawingPanel;
    
    /** Image icon for the about window. */
    private ImageIcon myImageIcon;
    
    /** Icon for current draw color. */
    private ColorIcon myColorIcon;
    
    /** Icon for current fill color. */
    private ColorIcon myFillIcon;
        
    /**
     * Constructor for the menu bar.
     * 
     * @param theFrame the frame for the application.
     * @param thePanel the drawing panel used to draw shapes.
     * @param theImage the image for the about window.
     */
    public MenuBar(final JFrame theFrame, final DrawingPanel thePanel, 
                   final BufferedImage theImage) {
        super();
        myDrawingPanel = thePanel;
        
        myClear = new JMenuItem("Clear");
        myQuit = new JMenuItem("Quit");
        mySlider = new JSlider(SLIDER_MIN, SLIDER_MAX, SLIDER_VALUE);
        myDrawColor = new JMenuItem("Draw Color...");
        myFillColor = new JMenuItem("Fill Color...");
        myFillCheckBox = new JCheckBoxMenuItem("Fill");
        myToolsMenu = new JMenu("Tools");
        myGroup = new ButtonGroup();
        myAbout = new JMenuItem("About...");
        myImageIcon = new ImageIcon(theImage);
        myColorIcon = new ColorIcon(myDrawingPanel.getCurrentColor());
        myFillIcon = new ColorIcon(myDrawingPanel.getCurrentFillColor());
        
        myDrawColor.setIcon(myColorIcon);
        myFillColor.setIcon(myFillIcon);
                
        addPropertyChangeListener(this);

        setup(theFrame);  
    }
    
    /**
     * Sets up the menu.
     * 
     * @param theFrame the frame for the application.
     */
    private void setup(final JFrame theFrame) {
        //Set up the file menu.
        final JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        createFileMenu(theFrame);

        //Set up the options menu.
        final JMenu options = new JMenu("Options");
        options.setMnemonic(KeyEvent.VK_O);
        
        //Set up the thickness sub-menu.
        final JMenu thickness = new JMenu("Thickness");
        thickness.setMnemonic(KeyEvent.VK_T);      
        
        //Create the options menu.
        createOptionsMenu();
        
        //Tools.
        myToolsMenu.setMnemonic(KeyEvent.VK_T);

        //Set up the help menu.
        final JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        createHelpMenu();
        
        //Add all items to appropriate menus.
        file.add(myClear);
        file.addSeparator();
        file.add(myQuit);
        
        options.add(thickness);
        thickness.add(mySlider);
        options.addSeparator();
        options.add(myDrawColor);
        options.add(myFillColor);
        options.addSeparator();
        options.add(myFillCheckBox);
        
        help.add(myAbout);
        
        //Add to menu bar.
        add(file);
        add(options);
        add(myToolsMenu);
        add(help);
    }
    
    /**
     * Creates the file menu and items.
     * @param theFrame the frame for the application.
     */
    private void createFileMenu(final JFrame theFrame) {
        //Set up the clear button.
        myClear.setMnemonic(KeyEvent.VK_C);
        myClear.setEnabled(false);
        myClear.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myDrawingPanel.clear();
                firePropertyChange("Cleared", true, false);
            }
        });
        
        //Set up the quit button.
        myQuit.setMnemonic(KeyEvent.VK_Q);
        myQuit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame, 
                                        WindowEvent.WINDOW_CLOSING));
            }
        });
    }
    
    /**
     * Creates the option menu and items.
     */
    private void createOptionsMenu() {
        //Create the slider.
        createSlider();
        //Set up the draw color button.
        myDrawColor.setMnemonic(KeyEvent.VK_D);
        myDrawColor.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(myDrawingPanel, 
                                      "Draw Color", 
                                      myDrawingPanel.getCurrentColor());
                if (result != null) {
                    myDrawingPanel.setColor(result);
                    myColorIcon.setColor(result);
                }
            }
        });
        
        //Set up the fill color button.
        myFillColor.setMnemonic(KeyEvent.VK_F);
        myFillColor.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(myDrawingPanel, 
                                             "Fill Color",
                                             myDrawingPanel.getCurrentFillColor());
                if (result != null) {
                    myDrawingPanel.setFillColor(result);
                    myFillIcon.setColor(result);
                }
            }
        });
        
        //Set up the fill check box.
        myFillCheckBox.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, 
                                                   ActionEvent.CTRL_MASK));
        myFillCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                final JCheckBoxMenuItem source = 
                                (JCheckBoxMenuItem) theEvent.getSource();
                if (source.isSelected()) {
                    myDrawingPanel.setFill(true);
                } else {
                    myDrawingPanel.setFill(false);
                }
            }
        });
        
    }
    
    /**
     * Creates the slider for stroke thickness.
     */
    private void createSlider() {
        mySlider.setExtent(SLIDER_EXTENT);
        mySlider.setMinorTickSpacing(SLIDER_MINOR);
        mySlider.setMajorTickSpacing(SLIDER_MAJOR);
        mySlider.setPaintLabels(true);
        mySlider.setPaintTicks(true);
        //Set up slider.
        mySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent theEvent) {
                final JSlider source = (JSlider) theEvent.getSource();
                if (!source.getValueIsAdjusting()) {
                    final int result = (int) source.getValue();
                    myDrawingPanel.setStroke(result);
                }
            }
        });
    }

    /**
     * Creates the tools menu and radio buttons.
     * Connects each button to buttons on the tool-bar.
     * @param theAction connects with tool-bar button.
     */
    public void createToolButton(final Action theAction) {
        final JRadioButtonMenuItem createdButton = 
                        new JRadioButtonMenuItem(theAction);
        myGroup.add(createdButton);
        myToolsMenu.add(createdButton);
    }
    
    /**
     * Creates the help menu and items.
     */
    private void createHelpMenu() {
        //Set up about button.
        myAbout.setMnemonic(KeyEvent.VK_A);
        myAbout.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(myDrawingPanel, 
                                              ABOUT, myAbout.getText(), 
                                              JOptionPane.INFORMATION_MESSAGE, 
                                              myImageIcon);

            }
        });
        
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        final Boolean value = true;
        if (value.equals(theEvent.getNewValue())) {
            myClear.setEnabled(true);
        } else {
            myClear.setEnabled(false);
        }
        
    }

}
