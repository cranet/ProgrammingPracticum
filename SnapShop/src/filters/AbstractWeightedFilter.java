/*
 * TCSS 305 - SnapShop
 */

package filters;

import image.PixelImage;

/**
 * Provides common behavior for all weighted filters.
 * 
 * @author Alan Fowler (acfowler@u.washington.edu)
 * @version 1.1
 */
public abstract class AbstractWeightedFilter extends AbstractFilter {

    /**
     * A 3x3 grid of weights to use for the weighted filter algorithm.
     */
    private final int[][] myWeights;

    /**
     * Constructs a new weighted filter.
     * 
     * @param theDescription a descriptive name for this filter
     * @param theWeights the 3x3 grid of weights to use
     */
    public AbstractWeightedFilter(final String theDescription, final int[][] theWeights) {
        super(theDescription);
        myWeights = theWeights.clone();
    }

    /**
     * Filters the specified image.
     * 
     * @param theImage The image.
     */
    @Override
    public void filter(final PixelImage theImage) {
        weight(theImage, myWeights);
    }

}
