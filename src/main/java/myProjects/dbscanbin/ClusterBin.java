package myProjects.dbscanbin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ClusterBin holding a set of {@link ClusterableBin} points.
 * @param <T> the type of points that can be clustered
 * @since 3.2
 */
public class ClusterBin<T extends ClusterableBin> implements Serializable {

    /** Serializable version identifier. */
    private static final long serialVersionUID = -3442297081515880464L;

    /** The points contained in this cluster. */
    private final List<T> points;

    /**
     * Build a cluster centered at a specified point.
     */
    public ClusterBin() {
        points = new ArrayList<T>();
    }

    /**
     * Add a point to this cluster.
     * @param point point to add
     */
    public void addPoint(final T point) {
        points.add(point);
    }

    /**
     * Get the points contained in the cluster.
     * @return points contained in the cluster
     */
    public List<T> getPoints() {
        return points;
    }

}
