package myProjects.dbscanbin;

import java.io.Serializable;

public class BinaryPoint  implements ClusterableBin, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long coordinates;
	Integer weight;
	
	BinaryPoint(Long crdn, int wgt)
	{
		coordinates = crdn;
		weight = wgt;		
	}

	public BinaryPoint getPoint() {
		return this;
	}
}
