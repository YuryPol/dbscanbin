package myProjects.dbscanbin;

import java.util.Collection;
import java.util.List;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;

/**
 * Base class for clustering algorithms.
 *
 * @param <T> the type of points that can be clustered
 * @since 3.2
 */
public abstract class ClustererBin<T extends ClusterableBin> {

    /** The distance measure to use. */
    private DistanceMeasureBin measure;

    /**
     * Build a new clusterer with the given {@link DistanceMeasure}.
     *
     * @param measure the distance measure to use
     */
    protected ClustererBin(final DistanceMeasureBin measure) {
        this.measure = measure;
    }

    /**
     * Perform a cluster analysis on the given set of {@link ClusterableBin} instances.
     *
     * @param points the set of {@link ClusterableBin} instances
     * @return a {@link List} of clusters
     * @throws MathIllegalArgumentException if points are null or the number of
     *   data points is not compatible with this clusterer
     * @throws ConvergenceException if the algorithm has not yet converged after
     *   the maximum number of iterations has been exceeded
     */
    public abstract List<? extends ClusterBin<T>> cluster(Collection<T> points)
            throws MathIllegalArgumentException, ConvergenceException;

    /**
     * Returns the {@link DistanceMeasure} instance used by this clusterer.
     *
     * @return the distance measure
     */
    public DistanceMeasureBin getDistanceMeasure() {
        return measure;
    }

    /**
     * Calculates the distance between two {@link ClusterableBin} instances
     * with the configured {@link DistanceMeasure}.
     *
     * @param p1 the first clusterable
     * @param p2 the second clusterable
     * @return the distance between the two clusterables
     */
    protected double distance(final ClusterableBin p1, final ClusterableBin p2) {
        return measure.compute(p1.getPoint(), p2.getPoint());
    }

}
