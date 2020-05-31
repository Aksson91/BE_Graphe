package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.algorithm.utils.*;
import org.insa.graphs.model.*;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    protected Label nouveauLabel(Node node, ShortestPathData data) {
    	return new LabelStar(node, data); 
    }
    
    
}
