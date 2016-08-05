/*
 * TCSS 305 - SnapShop
 */

package filters;

import image.PixelImage;

/**
 * An interface for filters that modify images.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 1.1
 */
public interface Filter {
    /**
     * Modifies the image according to this filter's algorithm.
     * 
     * @param theImage The image.
     */
    void filter(PixelImage theImage);

    /**
     * Returns a text description of this filter.
     * 
     * @return a text description of this filter.
     */
    String getDescription();
}
