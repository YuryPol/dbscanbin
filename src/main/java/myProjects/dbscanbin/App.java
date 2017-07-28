package myProjects.dbscanbin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App 
{
	static int MIN_VIEWERS_INGROUP_NUMBER = 30;
	static int MAX_SHOWS_PER_VIEWER = 15;
	
	public static void main(String[] args) {
		// Read the data file
	       String csvFile = args[0];
	       double eps = Double.valueOf(args[1]); 
	       int minPts = Integer.valueOf(args[2]);
	       BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        
	        Map<String, Long> showsMap = new HashMap<String, Long>();
	        List<BinaryPoint> points = new ArrayList<BinaryPoint>();

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            
	            if ((line = br.readLine()) != null)
	            {
	            	// analyze header
	            }
	            
	            long maxBit = 1;
	            while ((line = br.readLine()) != null) 
	            {
	                // use comma as separator
	                String[] shows = line.replace("[\"", "").replace("\"\"", "").replace("]\"", "").split(cvsSplitBy);
	                
                	if (shows.length > MAX_SHOWS_PER_VIEWER - 1)
                		continue; // viewed too many shows, probably TV junky or bot 
                	
	                int weight = Integer.valueOf(shows[shows.length - 1]);
	                if (weight <= MIN_VIEWERS_INGROUP_NUMBER) // the data were sorted in weight descended order
	                	continue; //too few viewers in the group
                	
        	        Long coordinates = new Long(0);
                	Long bits;
	                for(int ind = 0; ind < shows.length - 1; ind++)
	                {
	                	if (!showsMap.containsKey(shows[ind]))
	                	{
	                		// Add new show to the map
	                		showsMap.put(shows[ind], maxBit);
	                		maxBit = Long.rotateLeft(maxBit, 1);
	                	}
	                	if ((bits = showsMap.get(shows[ind])) != null)
	                	{
	                		coordinates |= bits;
	                	}
	                }
//	                System.out.println(Long.toBinaryString(coordinates));
                	// Add the viewer group to the list
                	points.add(new BinaryPoint(coordinates, weight));
	            }
	            System.out.println("Total points = " + points.size());
	            // Clustering
	            DBSCANClustererBin<BinaryPoint> dbscan = new DBSCANClustererBin<BinaryPoint>(eps, minPts); // (0.2, 5);
	            List<ClusterBin<BinaryPoint>> cluster = dbscan.cluster(points);
				FileWriter fw = new FileWriter(csvFile + ".out.bin.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Processing file " + csvFile + "\n");
				bw.write("Parameters: eps = " + eps + " minPts = " + minPts + "\n");
				bw.write("Total points = " + points.size() + "\n");
				bw.write("Clustering results\n");
				bw.write("there are  " + cluster.size() + " clusters\n");
	            for(ClusterBin<BinaryPoint> c: cluster)
	            {
	            	bw.write(" -- with " + c.getPoints().size() + " points\n");
	                for (BinaryPoint pt: c.getPoints())
	                {
	                	bw.write(String.format("%16s", Long.toBinaryString(pt.coordinates).replace('0', ' ').replace('1', '*')) + "\n");
	                }
	            }                       
				bw.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	}

}
