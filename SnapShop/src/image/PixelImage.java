/*
 * TCSS 305 - Assignment 3: SnapShop
 */

package image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Provides an interface to a picture as an array of pixels.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler - changes to variable naming and javadoc
 * @version 1.2
 */

public final class PixelImage extends BufferedImage {
    /**
     * An error to be displayed when arrays are improperly sized.
     */
    private static final String ARRAY_ERROR = "Array size is invalid.";

    // Constructor

    /**
     * Constructs a new PixelImage with the specified dimensions and type. This
     * constructor is not to be called externally.
     * 
     * @param theWidth The width.
     * @param theHeight The height.
     * @param theType The type.
     * @see java.awt.image#BufferedImage(int, int, int)
     */
    private PixelImage(final int theWidth, final int theHeight, final int theType) {
        super(theWidth, theHeight, theType);
    }

    // Static Methods

    /**
     * Loads an image from the specified file and returns a PixelImage
     * containing it.
     * 
     * @param theFile The file.
     * @return the PixelImage.
     * @exception IOException if there is a problem loading the image.
     */
    public static PixelImage load(final File theFile) throws IOException {
        final BufferedImage buf = ImageIO.read(theFile);

        if (buf == null) {
            throw new IOException("Not an image file!");
        }

        final PixelImage image =
                new PixelImage(buf.getWidth(), buf.getHeight(), BufferedImage.TYPE_INT_RGB);
        final Graphics g = image.getGraphics();
        g.drawImage(buf, 0, 0, null);
        return image;
    }

    // Instance Methods

    /**
     * Saves this PixelImage to the specified file, in PNG format.
     * 
     * @param theFile The file.
     * @exception IOException if there is a problem saving the image.
     */
    public void save(final File theFile) throws IOException {
        ImageIO.write(this, "png", theFile);
    }

    /**
     * Return the image's pixel data as an array of Pixels. The first coordinate
     * is the y-coordinate, so the size of the array is [height][width], where
     * width and height are the dimensions of the image.
     * 
     * @return the pixel data.
     */
    public Pixel[][] getPixelData() {
        final Raster r = getRaster();
        final Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
        int[] samples = new int[Pixel.NUM_CHANNELS];

        for (int row = 0; row < r.getHeight(); row++) {
            for (int col = 0; col < r.getWidth(); col++) {
                samples = r.getPixel(col, row, samples);
                final Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
                data[row][col] = newPixel;
            }
        }

        return data;
    }

    /**
     * Set the image's pixel data from an array. This array matches that
     * returned by getData(). It is an error to pass in an array that does not
     * match the image's dimensions or that has pixels with invalid values (not
     * 0-255).
     * 
     * @param theData The pixel data.
     * @exception IllegalArgumentException if the pixel data does not match the
     *                image dimensions or has invalid pixel values.
     */
    public void setPixelData(final Pixel[][] theData) throws IllegalArgumentException {
        final int[] pixelValues = new int[Pixel.NUM_CHANNELS];
        final WritableRaster wr = getRaster();

        if (theData == null || theData.length != wr.getHeight()) {
            throw new IllegalArgumentException(ARRAY_ERROR);
        } else if (theData[0].length != wr.getWidth()) {
            for (int i = 0; i < theData.length; i++) {
                if (theData[i] == null || theData[i].length != wr.getWidth()) {
                    throw new IllegalArgumentException(ARRAY_ERROR);
                }
            }
        }

        for (int row = 0; row < wr.getHeight(); row++) {
            for (int col = 0; col < wr.getWidth(); col++) {
                pixelValues[0] = theData[row][col].getRed();
                pixelValues[1] = theData[row][col].getGreen();
                pixelValues[2] = theData[row][col].getBlue();
                wr.setPixel(col, row, pixelValues);
            }
        }
    }
}
