package org.insa.graphs.algorithm.utils;
import org.insa.graphs.algorithm.shortestpath.*;
import org.insa.graphs.model.*;
import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.algorithm.AbstractInputData.Mode;

public class LabelStar extends Label {
	protected float EstimationCout;  
	
	protected double calculCoutEstime(int IdNoeud, ShortestPathData data) {
    	AbstractInputData.Mode mode = data.getMode(); 
    	double d = data.getGraph().get(IdNoeud).getPoint().distanceTo(data.getDestination().getPoint()); 
    	return (mode == Mode.LENGTH) ? d : (d/((Math.max(data.getGraph().getGraphInformation().getMaximumSpeed(), data.getMaximumSpeed()) / 3.6))); 
    }
	
	public LabelStar(Node noeud,  ShortestPathData data) {
		super(noeud);
		this.EstimationCout = (float) calculCoutEstime(noeud.getId(), data); 
	}
	
	public float getTotalCout() {
		return this.cout + this.EstimationCout; 
	}
}

