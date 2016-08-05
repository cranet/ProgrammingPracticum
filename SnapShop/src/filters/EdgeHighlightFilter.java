/*
 * TCSS 305 - SnapShop
 */

package filters;

/**
 * A filter that highlights edges in the image.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 1.1
 */
public class EdgeHighlightFilter extends AbstractWeightedFilter {
    /**
     * A 3x3 matrix of weights to use in the filtering algorithm.
     */
    private static final int[][] WEIGHTS = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};

    /**
     * Constructs a new edge highlighting filter.
     */
    public EdgeHighlightFilter() {
        super("Edge Highlight", WEIGHTS);
    }

}
