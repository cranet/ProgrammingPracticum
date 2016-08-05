/*
 * TCSS 305 - SnapShop
 */

package filters;

import image.PixelImage;

/**
 * Filter that converts the image to grayscale.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 1.1
 */
public class GrayscaleFilter extends AbstractFilter {
    /**
     * The mask for transforming the colors to grayscale.
     */
    private static final int MASK = 0xff;

    /**
     * The number of bits offset for the alpha channel.
     */
    private static final int ALPHA_OFFSET = 24;

    /**
     * The number of bits offset for the red channel.
     */
    private static final int RED_OFFSET = 16;

    /**
     * The number of bits offset for the green channel.
     */
    private static final int GREEN_OFFSET = 8;

    /**
     * A constant used to average the color values.
     */
    private static final int NUM_COLORS = 3;

    /**
     * Constructs a new grayscale filter.
     */
    public GrayscaleFilter() {
        super("Grayscale");
    }

    /**
     * Filters the specified image.
     * 
     * @param theImage The image.
     */
    @Override
    public void filter(final PixelImage theImage) {
        final int w = theImage.getWidth(null);
        final int h = theImage.getHeight(null);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                final int p = theImage.getRGB(i, j);
                final int q =
                        (((p >> RED_OFFSET) & MASK)
                                + ((p >> GREEN_OFFSET) & MASK)
                                + (p & MASK))
                                / NUM_COLORS;
                theImage.setRGB(i, j, (MASK << ALPHA_OFFSET) | (q << RED_OFFSET)
                                       | (q << GREEN_OFFSET) | q);
            }
        }
    }
}
