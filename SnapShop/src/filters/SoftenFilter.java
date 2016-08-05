/*
 * TCSS 305 - SnapShop
 */

package filters;

/**
 * A filter that makes the image less sharp.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 1.1
 */
public class SoftenFilter extends AbstractWeightedFilter {
    /**
     * A 3x3 grid of weights to use for the weighted filter algorithm.
     */
    private static final int[][] WEIGHTS = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};

    /**
     * Constructs a new softening filter.
     */
    public SoftenFilter() {
        super("Soften", WEIGHTS);
    }

}
