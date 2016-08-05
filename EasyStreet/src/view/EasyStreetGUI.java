/*
 * TCSS 305 - Easy Street
 */

package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Atv;
import model.Bicycle;
import model.Direction;
import model.Human;
import model.Light;
import model.Taxi;
import model.Terrain;
import model.Truck;
import model.Vehicle;

/**
 * The graphical user interface for the EasyStreet program.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler (acfowler@u.washington.edu)
 * @author Charles Bryan
 * @version Spring 2016
 */

public class EasyStreetGUI extends JFrame implements ActionListener {
    
    // static final fields (class constants)

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 0;

    /**
     * The window title.
     */
    private static final String TITLE = "Easy Street";

    /**
     * The filename of the city map.
     */
    private static final String CITY_FILE = "city_map1.txt";
    
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /**
     * The Start command.
     */
    private static final String START_COMMAND = "Start";

    /**
     * The Stop command.
     */
    private static final String STOP_COMMAND = "Stop";

    /**
     * The Step command.
     */
    private static final String STEP_COMMAND = "Step";

    /**
     * The Reset command.
     */
    private static final String RESET_COMMAND = "Reset";

    /**
     * The initial frames per second at which the simulation will run.
     */
    private static final int INITIAL_FRAMES_PER_SECOND = 10;

    /**
     * The maximum frames per second at which the simulation will run.
     */
    private static final int MAX_FRAMES_PER_SECOND = 60;

    /**
     * The size in pixels of a side of one "square" on the grid.
     */
    private static final int SQUARE_SIZE = 40;

    /**
     * The size in pixels of the directional markers drawn on vehicles in debug
     * mode.
     */
    private static final int MARKER_SIZE = 10;

    /**
     * The offset in pixels of the debug messages drawn for each square.
     */
    private static final int DEBUG_OFFSET = 10;
    
    /**
     * The offset to use to position cross walk lines and to use for cross walk light size.
     */
    private static final double CROSSWALK_SCALE = .25;

    /**
     * The number of clock ticks between light changes.
     */
    private static final int LIGHT_CHANGE_TICKS = 19;

    /**
     * The numerator for delay calculations.
     */
    private static final int MY_DELAY_NUMERATOR = 1000;

    /**
     * The minor tick spacing for the FPS slider.
     */
    private static final int MINOR_TICK_SPACING = 1;

    /**
     * The major tick spacing for the FPS slider.
     */
    private static final int MAJOR_TICK_SPACING = 10;

    /**
     * The stroke used for painting.
     */
    private static final BasicStroke STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT,
                                                              BasicStroke.JOIN_MITER, 2,
                                                              new float[] {2, 2, 2, 2}, 0);

    // Instance Fields
    
    /**
     * The delay between updates, based on the frames per second setting.
     */
    private int myDelay;

    /**
     * A timer used to update the state of the simulation.
     */
    private final Timer myTimer;

    /**
     * A list of vehicles in the simulation.
     */
    private final List<Vehicle> myVehicles;
    
    /**
     * The traffic lights in the simulation.
     */
    private Light myLights;
    
    /**
     * A flag indicating whether or not we are running in debug mode.
     */
    private boolean myDebugFlag;
    
    /**
     * The terrain grid for the simulation.
     */
    private Terrain[][] myGrid;

    /**
     * The current timestep of the simulation.
     */
    private long myTimestep;

    /**
     * The slider for "frames per second".
     */
    private JSlider mySlider;

    /**
     * The panel we render graphics on.
     */
    private EasyStreetPanel myPanel;

    // Constructor

    /**
     * Constructs a new EasyStreetGUI, using the files in the current working
     * directory.
     */
    public EasyStreetGUI() {
        super(TITLE);
        // initialize instance fields
        myDelay = MY_DELAY_NUMERATOR / INITIAL_FRAMES_PER_SECOND;
        myTimer = new Timer(myDelay, this);
        myVehicles = new ArrayList<Vehicle>();
        myLights = Light.GREEN;
        myDebugFlag = false;
        initGUI();
        setVisible(true);
    }
    
    // Instance Methods
    
    /**
     * Sets up the GUI.
     */
    private void initGUI() {
        try {
            readCity(CITY_FILE);
        } catch (final IOException ioe) {
            JOptionPane.showMessageDialog(this, "Could not read city map file " + CITY_FILE
                                              + ":\n\n" + ioe.getMessage(), "I/O Error",
                                          JOptionPane.ERROR_MESSAGE);
        }

        // set up graphical components
        myPanel = new EasyStreetPanel();

        mySlider = new JSlider(SwingConstants.HORIZONTAL, 0, MAX_FRAMES_PER_SECOND,
                               INITIAL_FRAMES_PER_SECOND);
        mySlider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        mySlider.setMinorTickSpacing(MINOR_TICK_SPACING);
        mySlider.setPaintLabels(true);
        mySlider.setPaintTicks(true);
        mySlider.addChangeListener(new ChangeListener() {
            /** Called in response to slider events in this window. */
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = mySlider.getValue();
                if (value > 0) {
                    myDelay = MY_DELAY_NUMERATOR / value;
                    myTimer.setDelay(myDelay);
                }
            }
        });

        final JCheckBox box = new JCheckBox("Debug Mode");
        box.addActionListener(this);

        // layout
        final Container northPanel = new JPanel(new FlowLayout());
        northPanel.add(makeButton(START_COMMAND));
        northPanel.add(makeButton(STOP_COMMAND));
        northPanel.add(makeButton(STEP_COMMAND));
        northPanel.add(makeButton(RESET_COMMAND));

        final Container southPanel = new JPanel(new FlowLayout());
        southPanel.add(new JLabel("FPS: "));
        southPanel.add(mySlider);
        southPanel.add(box);

        final Container masterPanel = new JPanel(new BorderLayout());
        masterPanel.add(myPanel, BorderLayout.CENTER);
        masterPanel.add(northPanel, BorderLayout.NORTH);
        masterPanel.add(southPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(masterPanel);
        pack();
        // position the frame in the center of the screen
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                    SCREEN_SIZE.height / 2 - getHeight() / 2);
    }
    
    /**
     * Reads the map from the specified file in the current working directory.
     * 
     * @param theFilename The filename.
     * @throws IOException if there is a problem reading the map.
     */
    private void readCity(final String theFilename) throws IOException {
        Scanner input;
        try {
            input = new Scanner(new File(theFilename));
        } catch (final IOException e) {
            input = new Scanner(getClass().getResourceAsStream(theFilename));
        }

        // First, we read the map description
        readGrid(input);

        // Then, we read where the initial vehicles are
        readVehicles(input);
    }
    
    /**
     * Reads the grid portion of the map file.
     * 
     * @param theInput The input scanner.
     */
    private void readGrid(final Scanner theInput) {
        final int numRows = theInput.nextInt();
        final int numColumns = theInput.nextInt();
        theInput.nextLine();
        myGrid = new Terrain[numRows][numColumns];
        for (int row = 0; row < numRows; row++) {
            final String line = theInput.nextLine();
            for (int column = 0; column < numColumns; column++) {
                myGrid[row][column] = Terrain.valueOf(line.charAt(column));
            }
        }
    }

    /**
     * Reads the vehicle portion of the map file.
     * 
     * @param theInput The input scanner.
     */
    private void readVehicles(final Scanner theInput) {
        final int numVehicles = theInput.nextInt();
        theInput.nextLine();
        for (int lineCount = 0; lineCount < numVehicles; lineCount++) {
            final char vehicleType = theInput.next().charAt(0);
            final int vehicleX = theInput.nextInt();
            final int vehicleY = theInput.nextInt();
            final char vehicleDirection = theInput.next().charAt(0);

            switch (vehicleType) {
                case 'B': // Bicycle
                    myVehicles.add(new Bicycle(vehicleX, vehicleY,
                                               Direction.valueOf(vehicleDirection)));
                    break;

                case 'A': //ATV
                    myVehicles.add(new Atv(vehicleX, vehicleY,
                                           Direction.valueOf(vehicleDirection)));
                    break;

                case 'H': // Human
                    myVehicles.add(new Human(vehicleX, vehicleY,
                                             Direction.valueOf(vehicleDirection)));
                    break;

                case 'T': // Truck
                    myVehicles.add(new Truck(vehicleX, vehicleY,
                                             Direction.valueOf(vehicleDirection)));
                    break;
                    
                case 'X': // Taxi
                    myVehicles.add(new Taxi(vehicleX, vehicleY,
                                            Direction.valueOf(vehicleDirection)));
                    break;

                default:
                    // this should never happen
                    assert false;
                    break;
            }
            theInput.nextLine();
        }
    }
    
    /**
     * Returns a new JButton with the specified text.
     * 
     * @param theText The text.
     * @return a new JButton.
     */
    private JButton makeButton(final String theText) {
        final JButton button = new JButton(theText);
        button.addActionListener(this);
        return button;
    }

    
    
    
    

    /**
     * A notification method called in response to action events in this window.
     * 
     * @param theEvent The action event that triggered the call.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final Object source = theEvent.getSource();
        if (source.equals(myTimer)) {
            // event came from the timer
            advanceAnimation();
        } else if (source instanceof JCheckBox) {
            // event came from the debug box
            final JCheckBox box = (JCheckBox) source;
            myDebugFlag = box.isSelected();
        } else {
            // event came from one of the buttons
            final String command = theEvent.getActionCommand().intern();
            if (command.equals(START_COMMAND)) {
                myTimer.start();
            } else if (command.equals(STOP_COMMAND)) {
                myTimer.stop();
            } else if (command.equals(STEP_COMMAND)) {
                advanceAnimation();
            } else if (command.equals(RESET_COMMAND)) {
                reset();
            }
        }

        myPanel.repaint();
    }

    /**
     * Advances the simulation by one frame of animation, moving each vehicle
     * once and checking collisions.
     */
    private void advanceAnimation() {
        for (final Vehicle v : myVehicles) {
            final Map<Direction, Terrain> neighbors = generateNeighbors(v);

            // move the vehicle
            if (v.isAlive()) {
                final Direction newDirection = v.chooseDirection(neighbors);
                v.setDirection(newDirection);

                // move one square in current direction, if it's okay to do so
                if (v.canPass(neighbors.get(newDirection), myLights)) {
                    v.setX(v.getX() + newDirection.dx());
                    v.setY(v.getY() + newDirection.dy());
                }
            } else {
                // become one move closer to revival
                v.poke();
            }

            // look for collisions
            for (final Vehicle other : myVehicles) {
                if (v == other) { // use of == is intentional - checking for same object
                    // don't collide with self
                    continue;
                }

                if (v.getX() == other.getX() && v.getY() == other.getY()) {
                    // tell both vehicles they have collided
                    v.collide(other);
                    other.collide(v);
                }
            }
        }

        myTimestep++;
        if (myTimestep % LIGHT_CHANGE_TICKS == 0) {
            myLights = myLights.advance();
        }
    }

    /**
     * Generates a read-only neighbors map for the specified vehicle.
     * 
     * @param theMover The vehicle.
     * @return The neighbors map.
     */
    private Map<Direction, Terrain> generateNeighbors(final Vehicle theMover) {
        final int x = theMover.getX();
        final int y = theMover.getY();
        final Map<Direction, Terrain> result = new HashMap<Direction, Terrain>();

        for (final Direction dir : Direction.values()) {
            if (isValidIndex(y + dir.dy(), x + dir.dx())) {
                result.put(dir, myGrid[y + dir.dy()][x + dir.dx()]);
            }
        }
        return Collections.unmodifiableMap(result);
    }

    /**
     * Tests whether the square at the given x/y position exists on the map.
     * 
     * @param theX The x position.
     * @param theY The y position.
     * @return true if the position exists on the map, false otherwise.
     */
    private boolean isValidIndex(final int theY, final int theX) {
        return 0 <= theY && theY < myGrid.length
            && 0 <= theX && theX < myGrid[theY].length;
    }


    /**
     * Resets all the vehicles to their initial locations, resets the tick
     * counter, and stops the simulation.
     */
    private void reset() {
        myTimer.stop();
        myTimestep = 0;
        myLights = Light.GREEN;

        for (final Vehicle mov : myVehicles) {
            mov.reset();
        }
    }



    /**
     * Draws the city map with the specified Graphics2D object.
     * 
     * @param theGraphics The Graphics2D object.
     */
    private void drawMap(final Graphics2D theGraphics) {
        for (int y = 0; y < myGrid.length; y++) {
            final int topy = y * SQUARE_SIZE;

            for (int x = 0; x < myGrid[y].length; x++) {
                final int leftx = x * SQUARE_SIZE;

                switch (myGrid[y][x]) {
                    case STREET:
                        theGraphics.setPaint(Color.LIGHT_GRAY);
                        theGraphics.fillRect(leftx, topy, SQUARE_SIZE, SQUARE_SIZE);
                        drawStreetLines(theGraphics, x, y);
                        break;

                    case WALL:
                        theGraphics.setPaint(Color.BLACK);
                        theGraphics.fillRect(leftx, topy, SQUARE_SIZE, SQUARE_SIZE);
                        break;

                    case TRAIL:
                        theGraphics.setPaint(Color.YELLOW.darker().darker());
                        theGraphics.fillRect(leftx, topy, SQUARE_SIZE, SQUARE_SIZE);
                        break;

                    case LIGHT:
                        // draw a circle of appropriate color
                        theGraphics.setPaint(Color.LIGHT_GRAY);
                        theGraphics.fillRect(leftx, topy, SQUARE_SIZE, SQUARE_SIZE);
                        setLightPaint(theGraphics);
                        theGraphics.fillOval(leftx, topy, SQUARE_SIZE, SQUARE_SIZE);
                        break;
                        
                    case CROSSWALK:
                        theGraphics.setPaint(Color.LIGHT_GRAY);
                        theGraphics.fillRect(leftx, topy, SQUARE_SIZE, SQUARE_SIZE);
                        
                        drawCrossWalkLines(theGraphics, x, y);
                        
                        // draw a small circle of appropriate color centered in the square
                        setLightPaint(theGraphics);
                        theGraphics.fillOval(leftx + (int)  (SQUARE_SIZE * CROSSWALK_SCALE),
                                             topy  + (int) (SQUARE_SIZE * CROSSWALK_SCALE),
                                             SQUARE_SIZE / 2, SQUARE_SIZE / 2);
                        break;

                    default:
                }

                drawDebugInfo(theGraphics, x, y);
            }
        }
    }
    
    /**
     * Draws debugging information, if necessary.
     * 
     * @param theGraphics The Graphics context to use for drawing.
     * @param theX The x-coordinate of the street.
     * @param theY The y-coordinate of the street.
     */
    private void drawDebugInfo(final Graphics2D theGraphics, final int theX, final int theY) {

        if (myDebugFlag) {
            // draw numbers for the row and column
            final Paint oldPaint = theGraphics.getPaint();
            theGraphics.setPaint(Color.BLACK);

            final int leftx = theX * SQUARE_SIZE;
            final int topy = theY * SQUARE_SIZE;
            theGraphics.drawString("(" + theX + ", " + theY + ")", leftx, topy + DEBUG_OFFSET);
            theGraphics.setPaint(oldPaint);
        }
    }
    
    /**
     * Draws the debug information for a single Vehicle.
     * 
     * @param theGraphics The graphic context.
     * @param theVehicle The Vehicle being drawn.
     */
    private void drawDebugInfo(final Graphics2D theGraphics, final Vehicle theVehicle) {
        int x = theVehicle.getX() * SQUARE_SIZE;
        int y = theVehicle.getY() * SQUARE_SIZE;

        // draw numbers on each vehicle
        theGraphics.setColor(Color.WHITE);
        theGraphics.drawString(theVehicle.toString(), x, y + SQUARE_SIZE - 1);
        theGraphics.setColor(Color.BLACK);
        theGraphics.drawString(theVehicle.toString(), x + 1, y + SQUARE_SIZE);

        // draw arrow on vehicle for its direction
        final Direction dir = theVehicle.getDirection();
        int dx = (SQUARE_SIZE - MARKER_SIZE) / 2;
        int dy = dx;

        switch (dir) {
            case WEST:
                dx = 0;
                break;

            case EAST:
                dx = SQUARE_SIZE - MARKER_SIZE;
                break;

            case NORTH:
                dy = 0;
                break;

            case SOUTH:
                dy = SQUARE_SIZE - MARKER_SIZE;
                break;

            default:
        }

        x = x + dx;
        y = y + dy;

        theGraphics.setColor(Color.YELLOW);
        theGraphics.fillOval(x, y, MARKER_SIZE, MARKER_SIZE);
    }

    /**
     * Sets the paint color appropriately for the current lights.
     * 
     * @param theGraphics The Graphics2D object on which to set the paint.
     */
    private void setLightPaint(final Graphics2D theGraphics) {
        switch (myLights) {
            case GREEN:
                theGraphics.setPaint(Color.GREEN.darker());
                break;

            case RED:
                theGraphics.setPaint(Color.RED.darker());
                break;

            case YELLOW:
                theGraphics.setPaint(Color.YELLOW);
                break;

            default:
        }
    }

    /**
     * Draws dotted lines on streets.
     * 
     * @param theGraphics The Graphics context to use for drawing.
     * @param theX The x-coordinate of the street.
     * @param theY The y-coordinate of the street.
     */
    private void drawStreetLines(final Graphics2D theGraphics,
                                 final int theX, final int theY) {
        
        final Paint oldPaint = theGraphics.getPaint();
        theGraphics.setPaint(Color.YELLOW);

        final int leftx = theX * SQUARE_SIZE;
        final int topy = theY * SQUARE_SIZE;
        final int centerx = leftx + SQUARE_SIZE / 2;
        final int centery = topy + SQUARE_SIZE / 2;
        final int rightx = leftx + SQUARE_SIZE;
        final int bottomy = topy + SQUARE_SIZE;

        if (isValidIndex(theY - 1, theX) && myGrid[theY - 1][theX] == Terrain.STREET) {
            theGraphics.drawLine(centerx, centery, centerx, topy); // above
        }
        if (isValidIndex(theY + 1, theX) && myGrid[theY + 1][theX] == Terrain.STREET) {
            theGraphics.drawLine(centerx, centery, centerx, bottomy); // below
        }
        if (isValidIndex(theY, theX - 1) && myGrid[theY][theX - 1] == Terrain.STREET) {
            theGraphics.drawLine(centerx, centery, leftx, centery); // left
        }
        if (isValidIndex(theY, theX + 1) && myGrid[theY][theX + 1] == Terrain.STREET) {
            theGraphics.drawLine(centerx, centery, rightx, centery); // right
        }

        theGraphics.setPaint(oldPaint);
    }
    
    /**
     * Draws dotted lines on cross walks.
     * 
     * @param theGraphics The Graphics context to use for drawing.
     * @param theX The x-coordinate of the cross walk.
     * @param theY The y-coordinate of the cross walk.
     */
    private void drawCrossWalkLines(final Graphics2D theGraphics,
                                 final int theX, final int theY) {
        
        final Paint oldPaint = theGraphics.getPaint();
        theGraphics.setPaint(Color.WHITE);

        final int leftx = theX * SQUARE_SIZE;
        final int topy = theY * SQUARE_SIZE;

        final int rightx = leftx + SQUARE_SIZE;
        final int bottomy = topy + SQUARE_SIZE;

        if (isValidIndex(theY - 1, theX) && myGrid[theY - 1][theX] == Terrain.GRASS) {
            // GRASS is above. Let's assume GRASS is also below.
            // DRAW lines on left and right of light
            theGraphics.drawLine(leftx  + (int) (SQUARE_SIZE * CROSSWALK_SCALE), topy,
                                 leftx  + (int) (SQUARE_SIZE * CROSSWALK_SCALE), bottomy);
            theGraphics.drawLine(rightx - (int) (SQUARE_SIZE * CROSSWALK_SCALE), topy,
                                 rightx - (int) (SQUARE_SIZE * CROSSWALK_SCALE), bottomy);
        }
        

        if (isValidIndex(theY, theX - 1) && myGrid[theY][theX - 1] == Terrain.GRASS) {
            // GRASS is left. Let's assume GRASS is also right.
            // DRAW lines above and below light
            theGraphics.drawLine(leftx, topy + (int) (SQUARE_SIZE * CROSSWALK_SCALE),
                                 rightx, topy + (int) (SQUARE_SIZE * CROSSWALK_SCALE));
            theGraphics.drawLine(leftx, bottomy - (int) (SQUARE_SIZE * CROSSWALK_SCALE),
                                 rightx, bottomy - (int) (SQUARE_SIZE * CROSSWALK_SCALE));
        }

        theGraphics.setPaint(oldPaint);
    }

    /**
     * A drawing panel for the map.
     */
    private class EasyStreetPanel extends JPanel {
        // Instance Fields

        /**
         * The UID of this class (to avoid warnings).
         */
        private static final long serialVersionUID = 4269666L;

        /**
         * The font used by this panel.
         */
        private final Font myFont = new Font("SansSerif", Font.BOLD, 9);

        // Constructor

        /**
         * Constructs a new panel.
         */
        EasyStreetPanel() {
            super();
            setPreferredSize(new Dimension(myGrid[0].length * SQUARE_SIZE,
                                           myGrid.length * SQUARE_SIZE));
            setBackground(Color.GREEN);
            setFont(myFont);
        }

        // Instance Methods

        /**
         * Paints this panel on the screen with the specified Graphics object.
         * 
         * @param theGraphics The Graphics object.
         */
        @Override
        public void paintComponent(final Graphics theGraphics) {
            super.paintComponent(theGraphics);
            final Graphics2D g2 = (Graphics2D) theGraphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setStroke(STROKE);

            // draw city map

            drawMap(g2);

            // draw vehicles
            for (final Vehicle v : myVehicles) {
                final String imageFilename = "icons//" + v.getImageFileName();
                //final String imageFilename = v.getImageFileName();
                ImageIcon imgIcon = new ImageIcon(imageFilename);

                if (imgIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                    imgIcon = new ImageIcon(getClass().getResource(imageFilename));
                }

                final Image img = imgIcon.getImage();
                g2.drawImage(img, v.getX() * SQUARE_SIZE, v.getY() * SQUARE_SIZE,
                             SQUARE_SIZE, SQUARE_SIZE, this);

                if (myDebugFlag) {
                    drawDebugInfo(g2, v);
                }
            }

            if (myDebugFlag) {
                g2.setColor(Color.WHITE);
                g2.drawString("Update # " + myTimestep, DEBUG_OFFSET / 2,
                              myFont.getSize() + DEBUG_OFFSET / 2);
            }
        }
    } // end class EasyStreetPanel
}

// end class EasyStreetGUI
