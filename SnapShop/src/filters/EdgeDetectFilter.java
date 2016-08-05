/*
 * TCSS 305 - SnapShop
 */

package filters;

import image.PixelImage;

/**
 * A filter that detects edges in the image.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 1.1
 */
public class EdgeDetectFilter extends AbstractFilter {
    /**
     * A 3x3 matrix of weights to use in the filtering algorithm.
     */
    private static final int[][] WEIGHTS = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};

    /**
     * Constructs a new edge detection filter.
     */
    public EdgeDetectFilter() {
        super("Edge Detect");
    }

    /**
     * Filters the specified image.
     * 
     * @param theImage The image.
     */
    @Override
    public void filter(final PixelImage theImage) {
        weight(theImage, WEIGHTS);
    }
}
