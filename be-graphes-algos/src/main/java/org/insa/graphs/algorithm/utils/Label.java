package org.insa.graphs.algorithm.utils;

//import org.insa.graphs.algorithm.shortestpath.Arc;
//import org.insa.graphs.algorithm.shortestpath.Label;

import org.insa.graphs.model.*;

public class Label implements Comparable<Label> {
    
	private boolean marked; 
	private boolean inTas; 
	private Node node;
	private Node father;
	protected float cost;

	/* ___________________________CONSTRUCTEUR____________________________________ */
	
	public Label(Node noeud){
		this.marked = false;
		this.inTas = false;
		this.node = noeud;
		this.father = null; 
		this.cost = Float.POSITIVE_INFINITY;
	}
	
	/* ______________________________SETTER_________________________________ */
	
	public void setMark() {
		this.marked = true;
	}
	
	public void setFather(Node father) {
		this.father = father;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public void setInTas() {
		this.inTas = true;
	} 
	
	/* ______________________________GETTER_________________________________ */

	
	public boolean getMark() {
		return this.marked;
	}
	
	public Node getFather() {
		return this.father;
	}
	
	public float getCost() {
		return this.cost;
	}
	
	public Node getNode() {
		return this.node;
	}

	public boolean getInTas() {
		return this.inTas;
	}	
	
	public float getTotalCost() {
		return this.cost;
	} 
	
	/* ______________________METHODE COMPARAISON COUT_________________________________________ */
	
	public int compareTo(Label autre) {
		int resultat;
		if (this.getTotalCost() < autre.getTotalCost()) {
			resultat = -1;
		}
		else if (this.getTotalCost() == autre.getTotalCost()) {
			resultat = 0;
		}
		else {
			resultat = 1;
		}
		return resultat;
	}
}