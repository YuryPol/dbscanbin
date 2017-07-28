package myProjects.dbscanbin;

import java.io.Serializable;

import org.apache.commons.math3.exception.DimensionMismatchException;

/**
 * Interface for distance measures of n-dimensional vectors.
 *
 * @since 3.2
 */
public interface DistanceMeasureBin extends Serializable {

    /**
     * Compute the distance between two n-dimensional vectors.
     * <p>
     * The two vectors are required to have the same dimension.
     *
     * @param a the first vector
     * @param b the second vector
     * @return the distance between the two vectors
     * @throws DimensionMismatchException if the array lengths differ.
     */
    double compute(BinaryPoint a, BinaryPoint b) throws DimensionMismatchException;
}
