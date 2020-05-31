package org.insa.graphs.algorithm.utils;

//import org.insa.graphs.algorithm.shortestpath.Arc;
//import org.insa.graphs.algorithm.shortestpath.Label;

import org.insa.graphs.model.*;

public class Label implements Comparable<Label> {
    
	private boolean marque; 
	private boolean inTas; 
	private Node pere;
	protected float cout;
	private Node node;

	/* ___________________________CONSTRUCTEUR____________________________________ */
	
	public Label(Node noeud){
		this.marque = false;
		this.inTas = false;
		this.node = noeud;
		this.pere = null; 
		this.cout = Float.POSITIVE_INFINITY;
	}
	
	/* ______________________________SETTER_________________________________ */
	
	public void setPere(Node father) {
		this.pere = father;
	}
	
	public void setCout(float cout) {
		this.cout = cout;
	}
	
	public void setMark() {
		this.marque = true;
	}
	
	public void setInTas() {
		this.inTas = true;
	} 
	
	/* ______________________________GETTER_________________________________ */

	
	public boolean getMark() {
		return this.marque;
	}
	
	public Node getPere() {
		return this.pere;
	}

	public boolean getInTas() {
		return this.inTas;
	}	
	
	public float getTotalCout() {
		return this.cout;
	} 
	
	public float getCout() {
		return this.cout;
	}
	
	public Node getNode() {
		return this.node;
	}

	
	/* ______________________METHODE COMPARAISON COUT_________________________________________ */
	
	public int compareTo(Label autre) {
		int resultat;
		if (this.getTotalCout() < autre.getTotalCout()) {
			resultat = -1;
		}
		else if (this.getTotalCout() == autre.getTotalCout()) {
			resultat = 0;
		}
		else {
			resultat = 1;
		}
		return resultat;
	}
}