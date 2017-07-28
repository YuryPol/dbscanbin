package myProjects.dbscanbin;
import org.apache.commons.math3.exception.DimensionMismatchException;
// import org.apache.commons.math3.util.MathArrays;

/**
 * Calculates the L<sub>2</sub> (Binary) distance between two points.
 *
 * @since 3.2
 */
public class BinaryDistance implements DistanceMeasureBin {

    /** Serializable version identifier. */
    private static final long serialVersionUID = 1717556319784040040L;

    /** {@inheritDoc} */
    public double compute(BinaryPoint a, BinaryPoint b)
    throws DimensionMismatchException {
    	// Implement binary distance calculations
    	double distance = (double)Long.bitCount(a.coordinates ^ b.coordinates) 
    			/ ((double)Long.bitCount(a.coordinates | b.coordinates) 
//    					* Math.log(a.weight) * Math.log(b.weight)
    					);
    	return distance;
        // return MathArrays.distance(a, b);
    }

}
